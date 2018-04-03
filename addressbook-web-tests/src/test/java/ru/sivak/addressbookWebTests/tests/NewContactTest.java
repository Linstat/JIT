package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Set;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.goTo().home();
        Set<NewContactParameters> before = app.contact().all();
        app.goTo().addNew();
        NewContactParameters contact = new NewContactParameters().withFirst("test");
        app.contact().create(contact, true);
        Set<NewContactParameters> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
