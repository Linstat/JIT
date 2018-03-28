package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.List;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.getNavigationHelper().clickGroups();
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
