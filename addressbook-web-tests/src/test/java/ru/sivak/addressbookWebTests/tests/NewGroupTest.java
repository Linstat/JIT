package ru.sivak.addressbookWebTests.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewGroupTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[] {new NewGroupParameters().withName(split[0]).withHead(split[1]).withFoot(split[2])});
            line = reader.readLine();
        }
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
