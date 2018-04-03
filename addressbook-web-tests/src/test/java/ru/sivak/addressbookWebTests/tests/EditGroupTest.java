package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.*;

public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
    }

    @Test
    public void testEditGroup() {
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        int index;
        index = app.getMathHelper().getRandom(0,(before.size()-1));
        NewGroupParameters group = new NewGroupParameters(before.get(index).getId(),"1234", "1234", "1234");
        app.getGroupHelper().editGroup(index, group);
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Comparator<? super NewGroupParameters> byId = Comparator.comparingInt(NewGroupParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
