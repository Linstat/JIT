package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.getNavigationHelper().clickHome();
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().clickAddNew();
        app.getContactHelper().fillNewContact(new NewContactParameters("Test", "Test", "Test", "Test", "Test", null), true);
        app.getContactHelper().clickEnter();
        app.getContactHelper().clickHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);
    }

}
