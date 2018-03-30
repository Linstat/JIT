package ru.sivak.addressbookWebTests.model;

import java.util.Objects;

public class NewGroupParameters {
    private final String name;
    private final String head;
    private final String foot;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewGroupParameters that = (NewGroupParameters) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "NewGroupParameters{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public NewGroupParameters(int id, String name, String head, String foot) {
        this.name = name;
        this.head = head;
        this.foot = foot;
        this.id = id;
    }

    public NewGroupParameters( String name, String head, String foot) {
        this.name = name;
        this.head = head;
        this.foot = foot;
        this.id = 0;
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

    public void setId(int id) {
        this.id = id;
    }

}
