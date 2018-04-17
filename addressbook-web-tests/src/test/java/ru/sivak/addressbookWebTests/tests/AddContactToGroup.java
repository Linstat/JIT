package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
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
public class AddContactToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.goTo().addNew();
            File photo = new File("src/test/resources/qwer.png");
            app.contact().create(new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123").withPhoto(photo), false);
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groups();
            app.group().create(new NewGroupParameters().withName("test"));
        }
        Groups groups = app.db().groups();
        if (app.contactHelper.findContactNotInGroup(app.db().contacts(), groups) == null) {
            app.goTo().groups();
            app.group().create(new NewGroupParameters().withName("newGroup"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        Groups groups = app.db().groups();
        NewContactParameters addedContact = app.contactHelper.findContactNotInGroup(app.db().contacts(), groups);
        NewGroupParameters toGroup = app.contactHelper.chooseGroup(addedContact, groups);
        Set<NewGroupParameters> gruopsBefore = app.db().contactInGroups(addedContact);
        app.goTo().home();
        app.contactHelper.addContactToGroup(addedContact, toGroup);
        Set<NewGroupParameters> gruopsAfter = app.db().contactInGroups(addedContact);
        gruopsBefore.add(toGroup);
        assertThat(gruopsAfter, equalTo(gruopsBefore));

    }
}
