package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Comparator;
import java.util.List;

public class EditContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().list().size()==0) {
            app.goTo().addNew();
            app.contact().create(new NewContactParameters("TestName", null, null, null, null), true);
        }
    }

    @Test
    public void testEditContact() {
        int index;
        List<NewContactParameters> before = app.contact().list();
        index = app.mathHelper().random(1,before.size());
        NewContactParameters contact = new NewContactParameters(before.get(index-1).getId(),"First", "Last", "97777777777", "edit@edit.edit", null);
        app.contact().edit(index, contact);
        List<NewContactParameters> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index-1);
        before.add(contact);
        Comparator<? super NewContactParameters> byId = Comparator.comparingInt(NewContactParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }


}
