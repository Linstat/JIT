package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.getNavigationHelper().clickHome();
        List<NewContactParameters> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().clickAddNew();
        app.getContactHelper().createContact(new NewContactParameters("Test","Test","Test","321654",null, null),true);
        List<NewContactParameters> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
