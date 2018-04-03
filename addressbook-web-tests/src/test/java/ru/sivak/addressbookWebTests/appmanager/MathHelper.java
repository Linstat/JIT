package ru.sivak.addressbookWebTests.appmanager;

import org.openqa.selenium.WebDriver;

public class MathHelper extends HelperBase {

    public MathHelper(WebDriver wd) {
        super(wd);
    }

    public int random(int min, int max){
        return min + (int) (Math.random()*max);
    }

}
