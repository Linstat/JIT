package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.getNavigationHelper().clickHome();
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().clickAddNew();
        NewContactParameters contact = new NewContactParameters("Test123","Test123","321654123",null, null);
        app.getContactHelper().createContact(contact,true);
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);
        Comparator<? super NewContactParameters> byId = Comparator.comparingInt(NewContactParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
