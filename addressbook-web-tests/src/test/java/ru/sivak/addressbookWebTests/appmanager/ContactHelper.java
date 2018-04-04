package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void clickHomePage() {
        click(By.linkText("home page"));
    }

    public void clickEnter() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillNewContact(NewContactParameters newContactParameters, boolean creation) {
        fillField(By.name("firstname"), newContactParameters.getFirst());
        fillField(By.name("lastname"), newContactParameters.getLast());
        fillField(By.name("mobile"), newContactParameters.getMobile());
        fillField(By.name("email"), newContactParameters.getEmail());
        if (creation) {
            if (newContactParameters.getGroup() != null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactParameters.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
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

    public void create(NewContactParameters contact, boolean creation) {
        fillNewContact(contact, creation);
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

    private Contacts contactCash = null;

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
            String mobile = element.findElement(By.xpath("td[6]")).getText();
            String email = element.findElement(By.xpath("td[5]")).getText();
            contactCash.add(new NewContactParameters().withId(id).withEmail(email).withFirst(first).withLast(last).withMobile(mobile));
        }
        return new Contacts(contactCash);
    }

    public boolean isContactHere() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return getCount("selected[]");
    }
}
