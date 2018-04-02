package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.ArrayList;
import java.util.List;

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
        if (creation){
            if (newContactParameters.getGroup()!=null) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactParameters.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void ClickDeleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void selectContact(int number) {
        selectElement("selected[]", number);
    }

    public void acceptDelete() {
        acceptAlert();
    }

    public void clickEdit(int number) {
        WebElement element = wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr["+(number+1)+"]/td[1]"));
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
        click(By.xpath("//a[@href='edit.php?id="+id+"']"));
    }

    public void clickUpdate() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void createContact(NewContactParameters contact, boolean creation) {
        fillNewContact(contact, creation);
        clickEnter();
        clickHomePage();
    }

    public boolean isContactHere() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return getCount("selected[]");
    }

    public List<NewContactParameters> getContactList() {
        List<NewContactParameters> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr[@name='entry']"));
        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String last = element.findElement(By.xpath("td[2]")).getText();
            String first = element.findElement(By.xpath("td[3]")).getText();
            String mobile = element.findElement(By.xpath("td[6]")).getText();
            String email = element.findElement(By.xpath("td[5]")).getText();
            NewContactParameters contact = new NewContactParameters(id,first,last,mobile,email,null);
            contacts.add(contact);
        }
        return contacts;
    }
}
