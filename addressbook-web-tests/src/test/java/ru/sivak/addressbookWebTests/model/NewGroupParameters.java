package ru.sivak.addressbookWebTests.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;

@XStreamAlias("contact")

public class NewGroupParameters {
    private String name;
    private String head;
    private String foot;
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;

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

     public String getName() {
        return name;
    }

    public String getHead() {
        return head;
    }

    public String getFoot() {
        return foot;
    }

    public NewGroupParameters withId(int id) {
        this.id = id;
        return this;
    }

    public NewGroupParameters withName(String name) {
        this.name = name;
        return this;
    }

    public NewGroupParameters withHead(String head) {
        this.head = head;
        return this;
    }

    public NewGroupParameters withFoot(String foot) {
        this.foot = foot;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewGroupParameters that = (NewGroupParameters) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, id);
    }
}
