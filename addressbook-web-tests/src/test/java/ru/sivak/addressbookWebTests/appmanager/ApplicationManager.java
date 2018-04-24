package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    public WebDriver wd;
    private String browser;
    public ContactHelper contactHelper;
    public SessionHelper sessionHelper;
    public NavigationHelper NavigationHelper;
    public GroupHelper groupHelper;
    public MathHelper mathHelper;
    public DbHelper dbHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper();
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseURL"));
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);
        NavigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        mathHelper = new MathHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPass"));
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return NavigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public MathHelper mathHelper() {
        return mathHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

}
