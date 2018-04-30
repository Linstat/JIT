package ru.sivak.bugify.tests;

import org.testng.SkipException;
import ru.sivak.bugify.appmanager.ApplicationManager;

import java.io.IOException;

/**
 * @author p.sivak.
 * @since 30.04.2018.
 */
public class TestBase {
    protected static ApplicationManager app = new ApplicationManager();

/*    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        app.stop();
    }*/

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        if (!app.ih().checkIssueState(issueId).equals("Resolved")) {
            return true;
        } else {
            return false;
        }
    }
}
