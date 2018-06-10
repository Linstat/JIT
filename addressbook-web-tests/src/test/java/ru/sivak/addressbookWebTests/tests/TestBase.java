package ru.sivak.addressbookWebTests.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.sivak.addressbookWebTests.appmanager.ApplicationManager;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author p.sivak.
 * @since 20.03.2018.
 */
public class TestBase {


    protected static ApplicationManager app;

    static {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    }

    org.slf4j.Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }


    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.groupHelper.all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new NewGroupParameters().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contactHelper.all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g) -> new NewContactParameters().withId(g.getId()).withFirst(g.getFirst()).withLast(g.getLast())).collect(Collectors.toSet())));
        }
    }
}
