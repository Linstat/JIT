package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

public class EditGroupTest extends TestBase {

    @Test
    public void testEditGroup() {
        app.getNavigationHelper().clickGroups();
        app.getGroupHelper().selectGroup("selected[]");
        app.getGroupHelper().clickEdit();
        app.getGroupHelper().fillNewGroup(new NewGroupParameters("1234", "1234", "1234"));
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().clickGroupPage();
    }
}
