package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewGroupTest extends TestBase {

    @Test
    public void createNewGroup() {
        app.goTo().groups();
        Groups before = app.group().all();
        NewGroupParameters group = new NewGroupParameters().withName("test");
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
