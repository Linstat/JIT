package ru.sivak.addressbookWebTests.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class DeleteGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groups();
        if (app.group().all().size() == 0) {
            app.group().create(new NewGroupParameters().withName("test"));
        }
    }


    @Test
    public void testDeleteGroup() {
        Groups before = app.group().all();
        NewGroupParameters deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedGroup)));
    }


}
