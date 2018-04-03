package ru.sivak.addressbookWebTests.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author p.sivak.
 * @since 03.04.2018.
 */
public class Contacts extends ForwardingSet<NewContactParameters> {

    private Set<NewContactParameters> deligate;

    public Contacts(Contacts contacts) {
        this.deligate = new HashSet<>(contacts.deligate);
    }

    public Contacts() {
        this.deligate = new HashSet<>();
    }

    @Override
    protected Set<NewContactParameters> delegate() {
        return deligate;
    }

    public Contacts withAdded(NewContactParameters contact) {
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(NewContactParameters contact) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }
}
