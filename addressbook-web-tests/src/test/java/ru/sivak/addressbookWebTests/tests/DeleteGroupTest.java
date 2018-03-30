package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;

public class DeleteGroupTest extends TestBase {
    int random;

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        random = app.getMathHelper().getRandom(0,before.size());
        app.getGroupHelper().selectGroup(random);
        app.getGroupHelper().clickDeleteGroup();
        app.getGroupHelper().clickGroupPage();
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(random);
        Assert.assertEquals(after,before);
    }

}
