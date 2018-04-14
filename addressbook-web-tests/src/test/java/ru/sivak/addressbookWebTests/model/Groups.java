package ru.sivak.addressbookWebTests.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author p.sivak.
 * @since 03.04.2018.
 */
public class Groups extends ForwardingSet<NewGroupParameters> {

    private Set<NewGroupParameters> deligate;

    public Groups(Groups groups) {
        this.deligate = new HashSet<>(groups.deligate);
    }

    public Groups() {
        this.deligate = new HashSet<>();
    }

    public Groups(Collection<NewGroupParameters> groups) {
        this.deligate = new HashSet<>(groups);
    }

    @Override
    protected Set<NewGroupParameters> delegate() {
        return deligate;
    }

    public Groups withAdded(NewGroupParameters group) {
        Groups groups = new Groups(this);
        groups.add(group);
        return groups;
    }

    public Groups without(NewGroupParameters group) {
        Groups groups = new Groups(this);
        groups.remove(group);
        return groups;
    }
}
