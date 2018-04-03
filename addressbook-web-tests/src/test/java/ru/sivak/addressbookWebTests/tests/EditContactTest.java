package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.contact().all();
        NewContactParameters editedContact = before.iterator().next();
        NewContactParameters contact = new NewContactParameters().withId(editedContact.getId())
                .withFirst("Edit").withEmail("edit").withLast("edit").withMobile("666");
        app.contact().edit(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
    }


}
