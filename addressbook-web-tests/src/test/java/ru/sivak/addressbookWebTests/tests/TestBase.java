package ru.sivak.addressbookWebTests.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.sivak.addressbookWebTests.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author p.sivak.
 * @since 20.03.2018.
 */
public class TestBase {


    org.slf4j.Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static ApplicationManager app;

    static {
        try {
            app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test "+ m.getName()+" with parameters "+ Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test "+ m.getName());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
