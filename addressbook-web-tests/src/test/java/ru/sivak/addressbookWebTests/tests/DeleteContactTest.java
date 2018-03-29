package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.List;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().clickHome();
        if (!app.getContactHelper().isContactHere()) {
            app.getNavigationHelper().clickAddNew();
            app.getContactHelper().createContact(new NewContactParameters("TestName", null, null,null,null,null), true);
        }
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(app.getMathHelper().getRandom(0,before.size()));
        app.getContactHelper().ClickDeleteContact();
        app.getContactHelper().acceptDelete();
        app.getNavigationHelper().clickHome();
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);

    }

}
