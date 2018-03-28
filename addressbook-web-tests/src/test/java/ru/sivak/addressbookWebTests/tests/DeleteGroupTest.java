package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;

public class DeleteGroupTest extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(app.getMathHelper().getRandom(0,before.size()));
        app.getGroupHelper().clickDeleteGroup();
        app.getGroupHelper().clickGroupPage();
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() -1);
    }

}
