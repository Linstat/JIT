package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.goTo().addNew();
            app.contact().create(new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123"), true);
        }
    }

    @Test
    public void testEditContact() {
        Contacts before = app.contact().all();
        NewContactParameters editedContact = before.iterator().next();
        NewContactParameters contact = new NewContactParameters().withId(editedContact.getId())
                .withFirst("Edit").withEmail1("edit").withLast("edit").withMobile("1234").withHome("1234").withWork("1234");
        app.contact().edit(contact);
        assertThat(app.contactHelper.count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
    }


}
