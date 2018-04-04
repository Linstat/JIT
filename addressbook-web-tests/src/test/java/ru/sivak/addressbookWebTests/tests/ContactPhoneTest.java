package ru.sivak.addressbookWebTests.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * @author p.sivak.
 * @since 04.04.2018.
 */
public class ContactPhoneTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().all().size() == 0) {
            app.goTo().addNew();
            app.contact().create(new NewContactParameters().withFirst("test").withMobile("123").withHome("123").withWork("123"), true);
        }
    }

    @Test
    public void testContactPhone() {
        app.goTo().home();
        NewContactParameters contact = app.contact().all().iterator().next();
        NewContactParameters contactInfoFormEditForm = app.contact().infoFormEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditForm)));
    }

    private String mergePhones(NewContactParameters contact) {
        return Arrays.asList(contact.getHome(),contact.getMobile(),contact.getWork())
                .stream().filter((s)->!s.equals(""))
                .map(this::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
