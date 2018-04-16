package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author p.sivak.
 * @since 04.04.2018.
 */
public class ContactInfoTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().home();
            app.goTo().addNew();
            app.contact().create(new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123"), true);
        }
    }

    @Test
    public void testContactInfo() {
        app.goTo().home();
        NewContactParameters contact = app.contactHelper.all().iterator().next();
        NewContactParameters contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFormEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFormEditForm.getAddress()));
    }

    private String mergePhones(NewContactParameters contact) {
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork())
                .stream().filter((s) -> !s.equals(""))
                .map(this::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmail(NewContactParameters contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
