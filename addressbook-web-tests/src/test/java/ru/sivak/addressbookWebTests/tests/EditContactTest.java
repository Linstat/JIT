package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Set;

public class EditContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.goTo().addNew();
            app.contact().create(new NewContactParameters().withFirst("test"), true);
        }
    }

    @Test
    public void testEditContact() {
        Set<NewContactParameters> before = app.contact().all();
        NewContactParameters editedContact = before.iterator().next();
        NewContactParameters contact = new NewContactParameters().withId(editedContact.getId())
                .withFirst("Edit").withEmail("edit").withLast("edit").withMobile("666");
        app.contact().edit(contact);
        Set<NewContactParameters> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(editedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }


}
