package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Contacts;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        app.goTo().home();
        Contacts before = app.contact().all();
        app.goTo().addNew();
        NewContactParameters contact = new NewContactParameters().withFirst("test");
        app.contact().create(contact, true);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
