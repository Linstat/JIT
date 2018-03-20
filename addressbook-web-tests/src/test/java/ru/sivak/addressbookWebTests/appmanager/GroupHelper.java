package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

public class GroupHelper {

    private FirefoxDriver wd;

    public GroupHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void clickGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void clickSubmit() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillNewGroup(NewGroupParameters newGroupParameters) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(newGroupParameters.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(newGroupParameters.getHead());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(newGroupParameters.getFoot());
    }

    public void clickNewGroup() {
        wd.findElement(By.name("new")).click();
    }

    public void clickDeleteGroup() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
    }
}
