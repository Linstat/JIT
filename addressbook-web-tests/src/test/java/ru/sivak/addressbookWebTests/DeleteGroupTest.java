package ru.sivak.addressbookWebTests;

import org.testng.annotations.Test;

public class DeleteGroupTest extends TestBase {

    @Test
    public void testDeleteGroup() {
        goToGroup();
        clickCheckBox();
        clickDeleteGroup();
        groupPage();
    }

}
