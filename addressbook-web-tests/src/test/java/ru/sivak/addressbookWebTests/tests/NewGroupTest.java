package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewGroupTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new NewGroupParameters().withName("test1").withHead("header1").withFoot("footer1")});
        list.add(new Object[]{new NewGroupParameters().withName("test2").withHead("header2").withFoot("footer2")});
        list.add(new Object[]{new NewGroupParameters().withName("test3").withHead("header3").withFoot("footer3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void createNewGroup(NewGroupParameters group) {
        app.goTo().groups();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.groupHelper.count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void createBadNewGroup() {
        app.goTo().groups();
        Groups before = app.group().all();
        NewGroupParameters group = new NewGroupParameters().withName("test'");
        app.group().create(group);
        assertThat(app.groupHelper.count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
