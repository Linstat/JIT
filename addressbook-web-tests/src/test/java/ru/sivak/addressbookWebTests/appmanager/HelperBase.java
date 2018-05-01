package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    public WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void fillField(By locator, String text) {
        click(locator);
        if (text != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if (! text.equals(existingText)){
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void getURL(String url) {
        wd.get(url);
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public int getCount(String name) {
        return wd.findElements(By.name(name)).size();
    }
}
