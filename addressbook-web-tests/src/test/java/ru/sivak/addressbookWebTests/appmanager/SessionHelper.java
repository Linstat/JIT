package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void logout() {
       click(By.linkText("Logout"));
    }

    public void login(String login, String password) {
        getURL("http://localhost/addressbook/");
        fillField(By.name("user"),login);
        fillField(By.name("pass"),password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }

}
