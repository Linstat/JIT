package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.HashSet;
import java.util.List;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.getNavigationHelper().clickHome();
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().clickAddNew();
        NewContactParameters contact = new NewContactParameters("Test","Test","321654",null, null);
        app.getContactHelper().createContact(contact,true);
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        int max = 0;
        for (NewContactParameters g : after){
            if (g.getId() > max){
                max = g.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}
