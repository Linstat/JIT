package ru.sivak.addressbookWebTests.generators;

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
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<NewGroupParameters> groups = generatorGroups(count);
        save(groups, file);

    }

    private static void save(List<NewGroupParameters> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (NewGroupParameters group : groups){
            writer.write(String.format("%s;%s;%s;\n", group.getName(),group.getHead(),group.getFoot()));
        }
        writer.close();
    }

    private static List<NewGroupParameters> generatorGroups(int count) {
        List<NewGroupParameters> groups = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            groups.add(new NewGroupParameters().withName(String.format("test %s", i)).withHead(String.format("header %s", i)).withFoot(String.format("footer %s", i)));
        }
        return groups;
    }
}
