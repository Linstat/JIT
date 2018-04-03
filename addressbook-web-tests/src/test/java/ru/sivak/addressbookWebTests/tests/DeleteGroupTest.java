package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;

public class DeleteGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
    }



    @Test
    public void testDeleteGroup() {
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        int index;
        index = app.getMathHelper().getRandom(0,(before.size()-1));
        app.getGroupHelper().deleteGroup(index);
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(index);
        Assert.assertEquals(after,before);
    }


}
