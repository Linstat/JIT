package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd=wd;
    }

    public void clickHome() {
        wd.findElement(By.linkText("home")).click();
    }

    public void clickAddNew() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void clickGroups() {
        wd.findElement(By.linkText("groups")).click();
    }
}
