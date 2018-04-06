package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new NewContactParameters().withFirst(split[0]).withLast(split[1])
                    .withMobile(split[2]).withAddress(split[3])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @Test(dataProvider = "validContacts")
    public void createNewContact(NewContactParameters contact) {
        File photo = new File("src/test/resources/qwer.png");
        contact.withPhoto(photo);
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNew();
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
