package ru.sivak.addressbookWebTests;

import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase {

    @Test
    public void testDeleteContact() {
        clickHome();
        clickCheckBox();
        ClickDeleteContact();
        acceptAlert();
    }

}
