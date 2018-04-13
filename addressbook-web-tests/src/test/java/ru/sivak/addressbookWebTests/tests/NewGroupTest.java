package ru.sivak.addressbookWebTests.tests;

import com.thoughtworks.xstream.XStream;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class NewGroupTest extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroups() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml = xml + line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(NewGroupParameters.class);
            List<NewGroupParameters> groups = (List<NewGroupParameters>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
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
