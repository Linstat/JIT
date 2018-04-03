package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewContactParameters;

import java.util.List;

public class DeleteContactTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().home();
        if (app.contact().list().size()==0) {
            app.goTo().addNew();
            app.contact().create(new NewContactParameters().withFirst("test"), true);
        }
    }

    @Test
    public void testDeleteContact() {
        int index;
        List<NewContactParameters> before = app.contact().list();
        index = app.mathHelper().random(0,(before.size()-1));
        app.contact().delete(index);
        app.goTo().home();
        List<NewContactParameters> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() -1);
        before.remove(index);
        Assert.assertEquals(after,before);
    }


}
