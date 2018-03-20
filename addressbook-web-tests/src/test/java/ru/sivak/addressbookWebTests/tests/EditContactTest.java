package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

public class EditContactTest extends TestBase {

    @Test
    public void testEditContact() {
        app.getNavigationHelper().clickHome();
        app.getContactHelper().selectContact("selected[]");
        app.getContactHelper().clickEdit();
        app.getContactHelper().fillNewContact(new NewContactParameters("Edit", "Editovich", "Editov", "97777777777", "edit@edit.edit"));
        app.getContactHelper().clickUpdate();
        app.getContactHelper().clickHomePage();
    }
}
