package ru.sivak.mantis.appmanager;

import org.openqa.selenium.By;
import ru.sivak.mantis.model.User;
import ru.sivak.mantis.model.Users;

/**
 * @author p.sivak.
 * @since 24.04.2018.
 */
public class LoginHelper extends HelperBase{
    public LoginHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String name, String pass){
        fillField(By.cssSelector("input[id='username']"),name);
        fillField(By.cssSelector("input[id='password']"),pass);
        click(By.cssSelector("input[type='submit']"));
    }

}
