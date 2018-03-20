package ru.sivak.addressbookWebTests;

import org.testng.annotations.Test;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        clickGroups();
        clickNewGroup();
        fillNewGroup(new NewGroupParameters("TestName", "TestHead", "TestFoot"));
        clickSubmit();
        clickGroupPage();
    }

}
