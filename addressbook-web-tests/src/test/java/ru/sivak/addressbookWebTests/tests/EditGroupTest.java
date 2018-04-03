package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Set;

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
        Set<NewGroupParameters> before = app.group().all();
        NewGroupParameters editedGroup = before.iterator().next();
        NewGroupParameters group = new NewGroupParameters().withId(editedGroup.getId())
                .withName("test1").withFoot("testf").withHead("testh");
        app.group().edit(group);
        Set<NewGroupParameters> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(editedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
