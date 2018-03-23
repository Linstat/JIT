package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.getNavigationHelper().clickAddNew();
        app.getContactHelper().fillNewContact(new NewContactParameters("Test", null, null, null, null, null), true);
        app.getContactHelper().clickEnter();
        app.getContactHelper().clickHomePage();
    }

}
