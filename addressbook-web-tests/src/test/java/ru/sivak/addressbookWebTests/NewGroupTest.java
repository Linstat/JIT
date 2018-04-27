package ru.sivak.addressbookWebTests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class NewGroupTest {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        login("admin","secret");
    }



    @Test
    public void createNewGroup() {
        goToGroup();
        clickNewGroup();
        fillNewGroup(new NewGroupParameters("TestName", "TestHead", "TestFoot"));
        submit();
        groupPage();
    }



    @AfterMethod
    public void tearDown() {
        logout();
        wd.quit();
    }

    private void login(String login, String password) {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(login);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void groupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    private void submit() {
        wd.findElement(By.name("submit")).click();
    }

    private void fillNewGroup(NewGroupParameters newGroupParameters) {
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

    private void clickNewGroup() {
        wd.findElement(By.name("new")).click();
    }

    private void goToGroup() {
        wd.findElement(By.linkText("groups")).click();
    }
}
