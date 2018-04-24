package ru.sivak.mantis.appmanager;

import org.openqa.selenium.By;
import ru.sivak.mantis.model.MailMessage;
import ru.sivak.mantis.model.User;
import ru.sivak.mantis.model.Users;

/**
 * @author p.sivak.
 * @since 24.04.2018.
 */
public class ChangePasswordHelper extends HelperBase{

    public ChangePasswordHelper(ApplicationManager app) {
        super(app);
    }

    public User chooseUser(Users users) {
        users.removeIf((u)->u.getUsername().equals("administrator"));
         User editedUser = users.iterator().next();
        return editedUser;
    }

    public void changePassword(User editedUser) {
        String userName = editedUser.getUsername();
        wd.findElement(By.xpath("//a[text()='"+userName+"']")).click();
        wd.findElement(By.cssSelector("input[value='Сбросить пароль']")).click();
    }

    public void finishChangePassword(String confirmationLink, String changedPassword) {
        wd.get(confirmationLink);
        fillField(By.name("password"), changedPassword);
        fillField(By.name("password_confirm"), changedPassword);
        click(By.cssSelector("input[value='Изменить учетную запись']"));
    }
}
