package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new NewContactParameters().withFirst("test1").withLast("header1").withAddress("footer1").withMobile("123").withHome("123").withWork("123")});
        list.add(new Object[]{new NewContactParameters().withFirst("test2").withLast("header2").withAddress("footer2").withMobile("1234").withHome("1234").withWork("1234")});
        list.add(new Object[]{new NewContactParameters().withFirst("test3").withLast("header3").withAddress("footer3").withMobile("1235").withHome("1235").withWork("1235")});
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void createNewContact(NewContactParameters contact) {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNew();
        //File photo = new File("src/test/resources/qwer.png");
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
