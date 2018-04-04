package ru.sivak.addressbookWebTests.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groups();
        if (app.group().all().size() == 0) {
            app.group().create(new NewGroupParameters().withName("test"));
        }
    }

    @Test
    public void testEditGroup() {
        Groups before = app.group().all();
        NewGroupParameters editedGroup = before.iterator().next();
        NewGroupParameters group = new NewGroupParameters().withId(editedGroup.getId())
                .withName("test1").withFoot("testf").withHead("testh");
        app.group().edit(group);
        assertThat(app.groupHelper.count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(editedGroup).withAdded(group)));
    }

}
