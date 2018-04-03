package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;

public class DeleteGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groups();
        if (app.group().list().size()==0) {
            app.group().create(new NewGroupParameters().withName("test"));
        }
    }



    @Test
    public void testDeleteGroup() {
        List<NewGroupParameters> before = app.group().list();
        int index;
        index = app.mathHelper().random(0,(before.size()-1));
        app.group().delete(index);
        List<NewGroupParameters> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(index);
        Assert.assertEquals(after,before);
    }


}
