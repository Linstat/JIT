package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.getNavigationHelper().clickGroups();
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        NewGroupParameters group = new NewGroupParameters("Test", null, null);
        app.getGroupHelper().createGroup(group);
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
        before.add(group);
        Comparator<? super NewGroupParameters> byId = Comparator.comparingInt(NewGroupParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
