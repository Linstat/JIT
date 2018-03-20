package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().clickHome();
        app.getContactHelper().selectContact("selected[]");
        app.getContactHelper().ClickDeleteContact();
        app.getContactHelper().acceptDelete();
    }

}
