package ru.sivak.addressbookWebTests;

import org.testng.annotations.Test;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        clickAddNew();
        fillNewContact(new NewContactParameters("Test", "Testovich", "Testov", "866666666666", "test@test.test"));
        clickEnter();
        clickHomePage();
    }

}
