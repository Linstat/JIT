package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;

public class GroupHelper extends HelperBase {


    public GroupHelper(WebDriver wd) {
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

    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void clickEdit() {
        click(By.name("edit"));
    }

    public void clickUpdate() {
        click(By.name("update"));
    }

    public void create(NewGroupParameters group) {
        clickNewGroup();
        fillNewGroup(group);
        clickSubmit();
        groupCash = null;
        clickGroupPage();
    }

    public void edit(NewGroupParameters group) {
        selectGroupById(group.getId());
        clickEdit();
        fillNewGroup(group);
        clickUpdate();
        groupCash = null;
        clickGroupPage();
    }

    public void delete(NewGroupParameters group) {
        selectGroupById(group.getId());
        clickDeleteGroup();
        groupCash = null;
        clickGroupPage();
    }

    public boolean isGroupHere() {
        return isElementPresent(By.name("selected[]"));
    }

    public void selectGroup(int number) {
        selectElement("selected[]", number);
    }

    private Groups groupCash = null;

    public Groups all() {
        if (groupCash != null) {
            return new Groups(groupCash);
        }
        groupCash = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCash.add(new NewGroupParameters().withId(id).withName(name));
        }
        return new Groups(groupCash);
    }

}
