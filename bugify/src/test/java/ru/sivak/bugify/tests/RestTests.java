package ru.sivak.bugify.tests;

import org.testng.annotations.Test;
import ru.sivak.bugify.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * @author p.sivak.
 * @since 30.04.2018.
 */
public class RestTests extends TestBase {
    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1230);
        Set<Issue> oldIssues = app.ih().getIssues();
        Issue newIssue = new Issue().withSubject("Subject").withDescription("Description");
        int issueId = app.ih().createIssue(newIssue);
        Set<Issue> newIssues = app.ih().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
