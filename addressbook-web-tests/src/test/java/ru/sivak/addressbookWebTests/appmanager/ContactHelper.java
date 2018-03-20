package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void clickHomePage() {
        click(By.linkText("home page"));
    }

    public void clickEnter() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillNewContact(NewContactParameters newContactParameters) {
        fillField(By.name("firstname"), newContactParameters.getFirst());
        fillField(By.name("middlename"), newContactParameters.getMiddle());
        fillField(By.name("lastname"), newContactParameters.getLast());
        fillField(By.name("mobile"), newContactParameters.getMobile());
        fillField(By.name("email"), newContactParameters.getEmail());
    }

    public void ClickDeleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void selectContact(String name) {
        click(By.name(name));
    }

    public void acceptDelete() {
        acceptAlert();
    }

}
