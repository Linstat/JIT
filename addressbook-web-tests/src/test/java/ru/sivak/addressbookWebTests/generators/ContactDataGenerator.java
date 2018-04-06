package ru.sivak.addressbookWebTests.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.sivak.addressbookWebTests.model.NewContactParameters;
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
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

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
        save(contacts, new File(file));
    }

    private static void save(List<NewContactParameters> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (NewContactParameters contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;\n", contact.getFirst(),contact.getLast(),contact.getMobile(),contact.getAddress()));
        }
        writer.close();
    }

    private static List<NewContactParameters> generatorContacts(int count) {
        List<NewContactParameters> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new NewContactParameters().withFirst(String.format("first %s", i)).withLast(String.format("last %s", i))
                    .withMobile(String.format("12345 %s", i)).withAddress(String.format("address %s", i)));
        }
        return contacts;
    }
}
