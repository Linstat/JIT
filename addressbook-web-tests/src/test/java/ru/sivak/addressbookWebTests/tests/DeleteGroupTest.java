package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {

    @Test
    public void testDeleteGroup() {
        app.clickGroups();
        app.clickCheckBox();
        app.clickDeleteGroup();
        app.clickGroupPage();
    }

}
