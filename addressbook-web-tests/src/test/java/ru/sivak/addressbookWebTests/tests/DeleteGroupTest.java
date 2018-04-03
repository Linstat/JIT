package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;
import java.util.Set;

public class DeleteGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groups();
        if (app.group().all().size()==0) {
            app.group().create(new NewGroupParameters().withName("test"));
        }
    }



    @Test
    public void testDeleteGroup() {
        Set<NewGroupParameters> before = app.group().all();
        NewGroupParameters deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<NewGroupParameters> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(deletedGroup);
        Assert.assertEquals(after,before);
    }


}
