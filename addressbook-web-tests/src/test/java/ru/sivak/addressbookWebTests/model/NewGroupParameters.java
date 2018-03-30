package ru.sivak.addressbookWebTests.model;

import java.util.Objects;

public class NewGroupParameters {
    private final String name;
    private final String head;
    private final String foot;

    public NewGroupParameters(String name, String head, String foot) {
        this.name = name;
        this.head = head;
        this.foot = foot;
    }

    public String getName() {
        return name;
    }

    public String getHead() {
        return head;
    }

    public String getFoot() {
        return foot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewGroupParameters that = (NewGroupParameters) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "NewGroupParameters{" +
                "name='" + name + '\'' +
                '}';
    }
}
