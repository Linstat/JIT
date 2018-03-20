package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().clickGroups();
        app.getGroupHelper().selectGroup("selected[]");
        app.getGroupHelper().clickDeleteGroup();
        app.getGroupHelper().clickGroupPage();
    }

}
