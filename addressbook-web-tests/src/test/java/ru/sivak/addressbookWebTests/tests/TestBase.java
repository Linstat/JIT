package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.sivak.addressbookWebTests.appmanager.ApplicationManager;

/**
 * @author p.sivak.
 * @since 20.03.2018.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
