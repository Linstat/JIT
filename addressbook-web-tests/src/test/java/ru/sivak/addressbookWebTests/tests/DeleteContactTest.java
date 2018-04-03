package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.List;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().clickHome();
        if (!app.getContactHelper().isContactHere()) {
            app.getNavigationHelper().clickAddNew();
            app.getContactHelper().createContact(new NewContactParameters("TestName", null, null, null, null), true);
        }
    }

    @Test
    public void testDeleteContact() {
        int index;
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        index = app.getMathHelper().getRandom(0,(before.size()-1));
        app.getContactHelper().deleteContact(index);
        app.getNavigationHelper().clickHome();
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(index);
        Assert.assertEquals(after,before);
    }


}
