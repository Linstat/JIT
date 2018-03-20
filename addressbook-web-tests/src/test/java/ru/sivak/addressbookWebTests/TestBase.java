package ru.sivak.addressbookWebTests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * @author p.sivak.
 * @since 20.03.2018.
 */
public class TestBase {
    FirefoxDriver wd;

    @BeforeMethod
    public void setUp() {
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:\\Users\\p.sivak\\AppData\\Local\\Mozilla Firefox\\firefox.exe"));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        login("admin", "secret");
    }

    @AfterMethod
    public void tearDown() {
        logout();
        wd.quit();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
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

    protected void clickHome() {
        wd.findElement(By.linkText("home")).click();
    }

    protected void clickHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    protected void clickEnter() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    protected void fillNewContact(NewContactParameters newContactParameters) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(newContactParameters.getFirst());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(newContactParameters.getMiddle());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(newContactParameters.getLast());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(newContactParameters.getMobile());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(newContactParameters.getEmail());
    }

    protected void clickAddNew() {
        wd.findElement(By.linkText("add new")).click();
    }

    protected void clickGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    protected void clickSubmit() {
        wd.findElement(By.name("submit")).click();
    }

    protected void fillNewGroup(NewGroupParameters newGroupParameters) {
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

    protected void clickNewGroup() {
        wd.findElement(By.name("new")).click();
    }

    protected void clickGroups() {
        wd.findElement(By.linkText("groups")).click();
    }

    protected void ClickDeleteContact() {
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    protected void clickCheckBox() {
        wd.findElement(By.name("selected[]")).click();
    }

    protected void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    protected void clickDeleteGroup() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
    }
}
