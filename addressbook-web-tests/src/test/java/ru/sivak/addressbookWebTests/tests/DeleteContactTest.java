package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().clickHome();
        if (!app.getContactHelper().isContactHere()) {
            app.getNavigationHelper().clickAddNew();
            app.getContactHelper().createContact(new NewContactParameters("TestName", null, null,null,null,null), true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact("selected[]");
        app.getContactHelper().ClickDeleteContact();
        app.getContactHelper().acceptDelete();
        app.getNavigationHelper().clickHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before -1);

    }

}
