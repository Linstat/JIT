package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.*;

public class ContactHelper extends HelperBase {

    private Contacts contactCash = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void clickHomePage() {
        click(By.linkText("home page"));
    }

    public void clickEnter() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillNewContact(NewContactParameters newContactParameters, boolean creationWithGroup) {
        fillField(By.name("firstname"), newContactParameters.getFirst());
        fillField(By.name("lastname"), newContactParameters.getLast());
        fillField(By.name("email"), newContactParameters.getEmail1());
        fillField(By.name("mobile"), newContactParameters.getMobile());
        fillField(By.name("home"), newContactParameters.getHome());
        fillField(By.name("work"), newContactParameters.getWork());
        fillField(By.name("address"), newContactParameters.getAddress());
        attach(By.name("photo"), newContactParameters.getPhoto());
        if (creationWithGroup) {
            if (newContactParameters.getGroups().size() > 0) {
                Assert.assertTrue(newContactParameters.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactParameters.getGroups().iterator().next().getName());
            }
        }
    }

    public void ClickDeleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptDelete() {
        acceptAlert();
    }

    private void editContactById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void clickUpdate() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(NewContactParameters contact, boolean creationWithGroup) {
        fillNewContact(contact, creationWithGroup);
        clickEnter();
        contactCash = null;
        clickHomePage();
    }

    public void edit(NewContactParameters contact) {
        editContactById(contact.getId());
        fillNewContact(contact, false);
        clickUpdate();
        contactCash = null;
        clickHomePage();
    }

    public void delete(NewContactParameters contact) {
        selectContactById(contact.getId());
        ClickDeleteContact();
        acceptDelete();
        contactCash = null;
    }

    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }
        contactCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String last = element.findElement(By.xpath("td[2]")).getText();
            String first = element.findElement(By.xpath("td[3]")).getText();
            String allPhones = element.findElement(By.xpath("td[6]")).getText();
            String allEmails = element.findElement(By.xpath("td[5]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            contactCash.add(new NewContactParameters().withId(id).withAllEmails(allEmails).withFirst(first).withLast(last).withAllPhones(allPhones).withAddress(address));
        }
        return new Contacts(contactCash);
    }

    public boolean isContactHere() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public NewContactParameters infoFormEditForm(NewContactParameters contact) {
        editContactById(contact.getId());
        String last = wd.findElement(By.name("lastname")).getAttribute("value");
        String first = wd.findElement(By.name("firstname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        wd.navigate().back();
        return new NewContactParameters().withId(contact.getId()).withFirst(first).withLast(last).withHome(home).withMobile(mobile).withWork(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    public NewContactParameters findContactNotInGroup(Contacts contacts, Groups groups) {
        NewContactParameters contactNotInGroup = null;
        for (NewContactParameters contact : contacts) {
            if (contact.getGroups().size() != groups.size()) {
                contactNotInGroup = contact;
                break;
            }
        }
        return contactNotInGroup;
    }

    public NewContactParameters findContactsInGroup(Contacts contacts) {
        NewContactParameters contactInGroup = null;
        for (NewContactParameters contact : contacts) {
            if (contact.getGroups().size() != 0) {
                contactInGroup = contact;
                break;
            }
        }
        return contactInGroup;
    }

    public NewGroupParameters chooseGroup(NewContactParameters addedContact, Groups groups) {
        groups.removeAll(addedContact.getGroups());
        return groups.iterator().next();
    }

    public void addContactToGroup(NewContactParameters addedContact, NewGroupParameters toGroup) {
        selectContactById(addedContact.getId());
        click(By.xpath("//select[@name='to_group']"));
        click(By.xpath("//select[@name='to_group']/option[@value='"+toGroup.getId()+"']"));
        click(By.xpath("//input[@type='submit']"));
    }


    public void deleteContactFromGroup(NewContactParameters contact, NewGroupParameters deletedGroup) {
        click(By.xpath("//form[@id='right']"));
        click(By.xpath("//form[@id='right']/select[@name='group']/option[@value='"+deletedGroup.getId()+"']"));
        selectContactById(contact.getId());
        click(By.xpath("//input[@name='remove']"));
    }
}
