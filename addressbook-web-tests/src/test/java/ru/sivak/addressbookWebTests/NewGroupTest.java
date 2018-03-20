package ru.sivak.addressbookWebTests;

import org.testng.annotations.Test;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        goToGroup();
        clickNewGroup();
        fillNewGroup(new NewGroupParameters("TestName", "TestHead", "TestFoot"));
        submit();
        groupPage();
    }

}
