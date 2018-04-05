package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.goTo().addNew();
            File photo = new File("src/test/resources/qwer.png");
            app.contact().create(new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123").withPhoto(photo), true);
        }
    }

    @Test
    public void testDeleteContact() {
        Contacts before = app.contact().all();
        NewContactParameters deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().home();
        assertThat(app.contactHelper.count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }


}
