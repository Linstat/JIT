package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.clickGroups();
        app.clickNewGroup();
        app.fillNewGroup(new NewGroupParameters("TestName", "TestHead", "TestFoot"));
        app.clickSubmit();
        app.clickGroupPage();
    }

}
