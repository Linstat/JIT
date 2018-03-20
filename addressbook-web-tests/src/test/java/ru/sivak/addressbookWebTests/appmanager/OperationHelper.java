package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OperationHelper {
    private FirefoxDriver wd;

    public OperationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void clickCheckBox() {
        wd.findElement(By.name("selected[]")).click();
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }
}
