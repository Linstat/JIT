package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groups();
            app.group().create(new NewGroupParameters().withName("test"));
        }
    }

    @Test
    public void testEditGroup() {
        Groups before = app.db().groups();
        NewGroupParameters editedGroup = before.iterator().next();
        NewGroupParameters group = new NewGroupParameters().withId(editedGroup.getId())
                .withName("test1").withFoot("testf").withHead("testh");
        app.goTo().groups();
        app.group().edit(group);
        assertThat(app.groupHelper.count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(editedGroup).withAdded(group)));
        verifyGroupListInUI();
    }

}
