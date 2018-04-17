package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author p.sivak.
 * @since 16.04.2018.
 */
public class DeleteContactFromGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groups();
            app.group().create(new NewGroupParameters().withName("test"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.goTo().addNew();
            File photo = new File("src/test/resources/qwer.png");
            app.contact().create(new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123").withPhoto(photo), true);
        }
        NewContactParameters contact = app.contactHelper.findContactsInGroup(app.db().contacts());
        if (contact == null) {
            contact = app.db().contacts().iterator().next();
            app.contactHelper.addContactToGroup(contact, app.db().groups().iterator().next());
        }
    }

    @Test
    public void testDeleteContactFromGroup() {
        Contacts contacts = app.db().contacts();
        NewContactParameters contact = app.contactHelper.findContactsInGroup(contacts);
        NewGroupParameters deletedGroup = app.db().contactInGroups(contact).iterator().next();
        app.goTo().home();
        Set<NewGroupParameters> befor = contact.getGroups();
        app.contactHelper.deleteContactFromGroup(contact, deletedGroup);
        Set<NewGroupParameters> after = app.db().contactInGroups(contact);
        befor.remove(deletedGroup);
        assertThat(after, equalTo(befor));
    }
}
