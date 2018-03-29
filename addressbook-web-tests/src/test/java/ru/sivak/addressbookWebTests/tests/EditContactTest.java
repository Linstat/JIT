package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.List;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact() {
        app.getNavigationHelper().clickHome();
        if (!app.getContactHelper().isContactHere()) {
            app.getNavigationHelper().clickAddNew();
            app.getContactHelper().createContact(new NewContactParameters("TestName", null, null,null,null,null), true);
        }
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(app.getMathHelper().getRandom(0,before.size()));
        app.getContactHelper().clickEdit();
        app.getContactHelper().fillNewContact(new NewContactParameters("Edit", "Editovich", "Editov", "97777777777", "edit@edit.edit", "1234"),false);
        app.getContactHelper().clickUpdate();
        app.getContactHelper().clickHomePage();
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
