package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class EditContactTest extends TestBase {
    int random;

    @Test
    public void testEditContact() {
        app.getNavigationHelper().clickHome();
        if (!app.getContactHelper().isContactHere()) {
            app.getNavigationHelper().clickAddNew();
            app.getContactHelper().createContact(new NewContactParameters("TestName", null, null,null,null), true);
        }
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        random = app.getMathHelper().getRandom(1,before.size());
        app.getContactHelper().clickEdit(random);
        NewContactParameters contact = new NewContactParameters(before.get(random-1).getId(),"First", "Last", "97777777777", "edit@edit.edit", null);
        app.getContactHelper().fillNewContact(contact,false);
        app.getContactHelper().clickUpdate();
        app.getContactHelper().clickHomePage();
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(random-1);
        before.add(contact);
        Comparator<? super NewContactParameters> byId = Comparator.comparingInt(NewContactParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
