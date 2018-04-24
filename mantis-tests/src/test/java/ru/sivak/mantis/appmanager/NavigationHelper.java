package ru.sivak.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * @author p.sivak.
 * @since 24.04.2018.
 */
public class NavigationHelper extends HelperBase {
    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void loginPage(){
        wd.get("http://localhost/mantisbt-1.3.14/login_page.php");
    }

    public void userPage(){
        wd.get("http://localhost/mantisbt-1.3.14/manage_user_page.php");
    }
}
