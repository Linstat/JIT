package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.HashSet;
import java.util.List;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.getNavigationHelper().clickGroups();
        List<NewGroupParameters> before = app.getGroupHelper().getGroupList();
        NewGroupParameters group = new NewGroupParameters("TestName", null, null);
        app.getGroupHelper().createGroup(group);
        List<NewGroupParameters> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
        int max = 0;
        for (NewGroupParameters g : after){
            if (g.getId() > max){
                max = g.getId();
            }
        }
        group.setId(max);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }

}
