package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class EditContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().clickHome();
        if (!app.getContactHelper().isContactHere()) {
            app.getNavigationHelper().clickAddNew();
            app.getContactHelper().createContact(new NewContactParameters("TestName", null, null, null, null), true);
        }
    }

    @Test
    public void testEditContact() {
        int index;
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        index = app.getMathHelper().getRandom(1,before.size());
        NewContactParameters contact = new NewContactParameters(before.get(index-1).getId(),"First", "Last", "97777777777", "edit@edit.edit", null);
        app.getContactHelper().editContact(index, contact);
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index-1);
        before.add(contact);
        Comparator<? super NewContactParameters> byId = Comparator.comparingInt(NewContactParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }


}
