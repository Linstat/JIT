package ru.sivak.addressbookWebTests.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.sivak.addressbookWebTests.appmanager.ApplicationManager;

/**
 * @author p.sivak.
 * @since 20.03.2018.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() {
        app.init(BrowserType.CHROME);
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
