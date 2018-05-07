package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.*;

public class EditGroupTest extends TestBase {
    int random;

    @Test
    public void testEditGroup() {
        app.getNavigationHelper().clickGroups();
        if (!app.getGroupHelper().isGroupHere()) {
            app.getGroupHelper().createGroup(new NewGroupParameters("TestName", null, null));
        }
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        random = app.getMathHelper().getRandom(0,(before.size()-1));
        app.getGroupHelper().selectGroup(random);
        app.getGroupHelper().clickEdit();
        NewGroupParameters group = new NewGroupParameters(before.get(random).getId(),"1234", "1234", "1234");
        app.getGroupHelper().fillNewGroup(group);
        app.getGroupHelper().clickUpdate();
        app.getGroupHelper().clickGroupPage();
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(random);
        before.add(group);
        Comparator<? super NewGroupParameters> byId = Comparator.comparingInt(NewGroupParameters::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }
}
