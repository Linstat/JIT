package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

public class EditGroupTest extends TestBase {

    @Test
    public void testEditGroup() {
        app.getNavigationHelper().clickGroups();
        int before = app.getGroupHelper().getGroupCount();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
        app.getGroupHelper().selectGroup(app.getMathHelper().getRandom(0,before));
        app.getGroupHelper().clickEdit();
        app.getGroupHelper().fillNewGroup(new NewGroupParameters("EditedName", "EditedHead", "EditedFoot"));
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().clickGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
    }
}
