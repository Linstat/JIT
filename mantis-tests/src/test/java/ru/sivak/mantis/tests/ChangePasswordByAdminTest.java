package ru.sivak.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.mantis.model.MailMessage;
import ru.sivak.mantis.model.User;
import ru.sivak.mantis.model.Users;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * @author p.sivak.
 * @since 24.04.2018.
 */
public class ChangePasswordByAdminTest extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePasswordByAdmin() throws IOException, MessagingException, ServiceException {
        skipIfNotFixed(0000001);
        String newPassword = "changedPassword";
        app.goTo().loginPage();
        app.login().login(app.getProperty("web.adminLogin"),app.getProperty("web.adminPass"));
        app.goTo().userPage();
        Users users = app.db().users();
        User editedUser = app.changePassword().chooseUser(users);
        app.changePassword().changePassword(editedUser);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, editedUser.getEmail());
        app.changePassword().finishChangePassword(confirmationLink, newPassword);
        assertTrue(app.newSession().login(editedUser.getUsername(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
