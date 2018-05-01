package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

public class DeleteGroupTest extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup("selected[]");
        app.getGroupHelper().clickDeleteGroup();
        app.getGroupHelper().clickGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before -1);
    }

}
