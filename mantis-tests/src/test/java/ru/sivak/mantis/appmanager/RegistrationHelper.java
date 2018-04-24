package ru.sivak.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app){
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseURL")+"/signup_page.php");
        fillField(By.name("username"), username);
        fillField(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finishRegistration(String confirmationLink, String password) {
        wd.get(confirmationLink);
        fillField(By.name("password"), password);
        fillField(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
}
