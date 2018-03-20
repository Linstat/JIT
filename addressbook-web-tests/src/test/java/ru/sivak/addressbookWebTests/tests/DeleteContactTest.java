package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        app.clickHome();
        app.clickCheckBox();
        app.ClickDeleteContact();
        app.acceptAlert();
    }

}
