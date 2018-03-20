package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.getNavigationHelper().clickGroups();
        app.getOperationHelper().clickCheckBox();
        app.getGroupHelper().clickDeleteGroup();
        app.getGroupHelper().clickGroupPage();
    }

}
