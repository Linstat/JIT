package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().clickHome();
        app.getOperationHelper().clickCheckBox();
        app.getContactHelper().ClickDeleteContact();
        app.getOperationHelper().acceptAlert();
    }

}
