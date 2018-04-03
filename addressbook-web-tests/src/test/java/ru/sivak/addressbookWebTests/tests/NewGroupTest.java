package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.goTo().groups();
        Set<NewGroupParameters> before = app.group().all();
        NewGroupParameters group = new NewGroupParameters().withName("test");
        app.group().create(group);
        Set<NewGroupParameters> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before,after);
    }

}
