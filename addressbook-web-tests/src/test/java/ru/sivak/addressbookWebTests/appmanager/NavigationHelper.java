package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void clickHome() {
        click(By.linkText("home"));
    }

    public void clickAddNew() {
        click(By.linkText("add new"));
    }

    public void clickGroups() {
        click(By.linkText("groups"));
    }
}
