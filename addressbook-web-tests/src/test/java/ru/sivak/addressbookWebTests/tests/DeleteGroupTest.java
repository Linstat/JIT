package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groups();
            app.group().create(new NewGroupParameters().withName("test"));
        }
    }


    @Test
    public void testDeleteGroup() {
        app.goTo().groups();
        Groups before = app.db().groups();
        NewGroupParameters deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.groupHelper.count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyContactListInUI();
    }


}
