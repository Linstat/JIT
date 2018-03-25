package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver wd;
    public ContactHelper contactHelper;
    public SessionHelper sessionHelper;
    public NavigationHelper NavigationHelper;
    public GroupHelper groupHelper;
    public MathHelper mathHelper;

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public void init(String browser) {
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);
        NavigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        mathHelper = new MathHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return NavigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public MathHelper getMathHelper () {
        return mathHelper;
    }

}
