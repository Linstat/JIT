package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;

public class EditGroupTest extends TestBase {

    @Test
    public void testEditGroup() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(app.getMathHelper().getRandom(0,before.size()));
        app.getGroupHelper().clickEdit();
        app.getGroupHelper().fillNewGroup(new NewGroupParameters("1234", "1234", "1234"));
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().clickGroupPage();
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }
}
