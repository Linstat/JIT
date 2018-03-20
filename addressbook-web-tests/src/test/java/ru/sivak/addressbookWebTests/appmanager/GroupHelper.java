package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void clickGroupPage() {
        click(By.linkText("group page"));
    }

    public void clickSubmit() {
        click(By.name("submit"));
    }

    public void fillNewGroup(NewGroupParameters newGroupParameters) {
        fillField(By.name("group_name"), newGroupParameters.getName());
        fillField(By.name("group_header"), newGroupParameters.getHead());
        fillField(By.name("group_footer"), newGroupParameters.getFoot());
    }

    public void clickNewGroup() {
        click(By.name("new"));
    }

    public void clickDeleteGroup() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void selectGroup(String name) {
        click(By.name(name));
    }
}
