package ru.sivak.addressbookWebTests.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
//        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            xml = xml + line;
            //           String[] split = line.split(";");
            //           list.add(new Object[] {new NewGroupParameters().withName(split[0]).withHead(split[1]).withFoot(split[2])});
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(NewContactParameters.class);
        List<NewContactParameters> groups = (List<NewContactParameters>)xstream.fromXML(xml);
        return groups.stream().map((g)-> new Object[]{g}).collect(Collectors.toList()).iterator();
//        return list.iterator();
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
