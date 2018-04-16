package ru.sivak.addressbookWebTests.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.sivak.addressbookWebTests.model.NewGroupParameters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author p.sivak.
 * @since 05.04.2018.
 */
public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<NewGroupParameters> groups = generatorGroups(count);
        if (format.equals("csv")) {
            saveAsCsv(groups, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(groups, new File(file));
        }
    }

    private void saveAsXml(List<NewGroupParameters> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(NewGroupParameters.class);
        xstream.alias("group", NewGroupParameters.class);
        String xml = xstream.toXML(groups);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<NewGroupParameters> groups, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (NewGroupParameters group : groups) {
                writer.write(String.format("%s;%s;%s;\n", group.getName(), group.getHead(), group.getFoot()));
            }
        }
    }

    private List<NewGroupParameters> generatorGroups(int count) {
        List<NewGroupParameters> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new NewGroupParameters().withName(String.format("test %s", i)).withHead(String.format("header\n%s", i)).withFoot(String.format("footer\n%s", i)));
        }
        return groups;
    }
}
