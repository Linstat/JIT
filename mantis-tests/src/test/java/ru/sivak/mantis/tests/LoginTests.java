package ru.sivak.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.*;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root1"));
        assertTrue(session.isLoggedInAs("administrator"));
    }

}
