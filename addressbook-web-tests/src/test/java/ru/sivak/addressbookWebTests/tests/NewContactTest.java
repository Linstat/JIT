package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Comparator;
import java.util.List;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.goTo().home();
        List<NewContactParameters> before = app.contact().list();
        app.goTo().addNew();
        NewContactParameters contact = new NewContactParameters("Test123",null,null,"asdf", null);
        app.contact().create(contact,true);
        List<NewContactParameters> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(contact);
        Comparator<? super NewContactParameters> byId = Comparator.comparingInt(NewContactParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
