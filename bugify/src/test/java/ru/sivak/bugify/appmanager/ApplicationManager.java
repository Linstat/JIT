package ru.sivak.bugify.appmanager;


/**
 * @author p.sivak.
 * @since 30.04.2018.
 */
public class ApplicationManager {
    private IssueHelper issueHelper;


/*    public void stop() {
    }

    public void init() {
    }*/

    public IssueHelper ih() {
        if (issueHelper == null) {
            issueHelper = new IssueHelper(this);
        }
        return issueHelper;
    }

}
