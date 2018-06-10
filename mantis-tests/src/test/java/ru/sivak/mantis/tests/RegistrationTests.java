package ru.sivak.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        app.registration().finishRegistration(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
    }



    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}