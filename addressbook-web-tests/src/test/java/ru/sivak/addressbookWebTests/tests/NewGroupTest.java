package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Comparator;
import java.util.List;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.goTo().groups();
        List<NewGroupParameters> before = app.group().list();
        NewGroupParameters group = new NewGroupParameters().withName("test");
        app.group().create(group);
        List<NewGroupParameters> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(group);
        Comparator<? super NewGroupParameters> byId = Comparator.comparingInt(NewGroupParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
