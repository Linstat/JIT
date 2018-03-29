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
        fillField(By.name("middlename"), newContactParameters.getMiddle());
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

    public void clickEdit() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
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
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']/td[2]"));
        for (WebElement element : elements){
            String name = element.getText();
            NewContactParameters contact = new NewContactParameters(name,null,null,null,null,null);
            contacts.add(contact);
        }
        return contacts;
    }
}
