package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNew();
        File photo = new File("src/test/resources/qwer.png");
        NewContactParameters contact = new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123").withPhoto(photo);
        app.contact().create(contact, true);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void createNewBadContact() {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNew();
        File photo = new File("src/test/resources/qwer.png");
        NewContactParameters contact = new NewContactParameters().withFirst("test'").withMobile("123").withHome("123").withWork("123").withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contactHelper.count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
