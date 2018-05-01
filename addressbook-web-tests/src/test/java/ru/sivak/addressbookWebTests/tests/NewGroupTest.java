package ru.sivak.addressbookWebTests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.getNavigationHelper().clickGroups();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().clickNewGroup();
        app.getGroupHelper().fillNewGroup(new NewGroupParameters("TestName", null, null));
        app.getGroupHelper().clickSubmit();
        app.getGroupHelper().clickGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

}
