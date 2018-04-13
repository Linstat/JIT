package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0){
            app.goTo().home();
            app.goTo().addNew();
            File photo = new File("src/test/resources/qwer.png");
            app.contact().create(new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123").withPhoto(photo), true);
        }

    }

    @Test
    public void testEditContact() {
        Contacts before = app.db().contacts();
        NewContactParameters editedContact = before.iterator().next();
        File photo = new File("src/test/resources/qwer.png");
        NewContactParameters contact = new NewContactParameters().withId(editedContact.getId())
                .withFirst("Edit").withEmail1("edit").withLast("edit").withMobile("1234").withHome("1234").withWork("1234").withPhoto(photo);
        app.goTo().home();
        app.contact().edit(contact);
        assertThat(app.contactHelper.count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(editedContact).withAdded(contact)));
    }


}
