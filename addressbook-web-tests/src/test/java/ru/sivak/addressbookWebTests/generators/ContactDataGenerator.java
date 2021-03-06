package ru.sivak.addressbookWebTests.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.sivak.addressbookWebTests.model.Groups;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
import ru.sivak.addressbookWebTests.tests.TestBase;

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
public class ContactDataGenerator extends TestBase {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
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
        List<NewContactParameters> contacts = generatorContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        }
    }

    private void saveAsXml(List<NewContactParameters> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(NewContactParameters.class);
        xstream.alias("contact", NewContactParameters.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private static void saveAsCsv(List<NewContactParameters> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (NewContactParameters contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;\n", contact.getFirst(), contact.getLast(), contact.getMobile(), contact.getAddress()));
            }
        }
    }

    private static List<NewContactParameters> generatorContacts(int count) {
        Groups groups = app.db().groups();
        List<NewContactParameters> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new NewContactParameters().withFirst(String.format("first %s", i)).withLast(String.format("last %s", i))
                    .withMobile(String.format("12345 %s", i)).withAddress(String.format("address %s", i)));
        }
        return contacts;
    }
}
