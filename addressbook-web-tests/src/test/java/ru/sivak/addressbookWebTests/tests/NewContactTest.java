package ru.sivak.addressbookWebTests.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml = xml + line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(NewContactParameters.class);
            List<NewContactParameters> contacts = (List<NewContactParameters>) xstream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groups();
            app.group().create(new NewGroupParameters().withName("test"));
        }
    }

    @Test(dataProvider = "validContacts")
    public void createNewContact(NewContactParameters contactFromFile) {
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/qwer.png");
        NewContactParameters contact = new NewContactParameters().inGroup(groups.iterator().next()).withPhoto(photo)
                .withLast(contactFromFile.getLast()).withFirst(contactFromFile.getFirst()).withMobile(contactFromFile.getMobile()).withAddress(contactFromFile.getAddress());
        app.goTo().home();
        Contacts before = app.db().contacts();
        app.goTo().addNew();
        app.contact().create(contact, true);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test
    public void createNewBadContact() {
        app.goTo().home();
        Contacts before = app.db().contacts();
        app.goTo().addNew();
        File photo = new File("src/test/resources/qwer.png");
        NewContactParameters contact = new NewContactParameters().withFirst("test'").withMobile("123").withHome("123").withWork("123").withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contactHelper.count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }
}
