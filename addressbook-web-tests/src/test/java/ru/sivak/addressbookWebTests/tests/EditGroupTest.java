package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

public class EditGroupTest extends TestBase {

    @Test
    public void testEditGroup() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
        app.getGroupHelper().selectGroup("selected[]");
        app.getGroupHelper().clickEdit();
        app.getGroupHelper().fillNewGroup(new NewGroupParameters("EditedName", "EditedHead", "EditedFoot"));
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().clickGroupPage();
    }
}
