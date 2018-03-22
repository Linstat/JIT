package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.getNavigationHelper().clickGroups();
        app.getGroupHelper().clickNewGroup();
        app.getGroupHelper().fillNewGroup(new NewGroupParameters("TestName", null, null));
        app.getGroupHelper().clickSubmit();
        app.getGroupHelper().clickGroupPage();
    }

}
