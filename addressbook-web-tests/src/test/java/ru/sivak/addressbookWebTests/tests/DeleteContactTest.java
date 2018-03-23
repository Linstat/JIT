package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        if (!app.getContactHelper().isContactHere()) {
            app.getNavigationHelper().clickAddNew();
            app.getContactHelper().createContact(new NewContactParameters("TestName", null, null,null,null,null), true);
        }
        app.getContactHelper().selectContact("selected[]");
        app.getContactHelper().ClickDeleteContact();
        app.getContactHelper().acceptDelete();
    }

}
