package ru.sivak.addressbookWebTests.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")

public class NewGroupParameters {
    @Column(name = "group_name")
    private String name;
    @Column(name = "group_header")
    @Type(type = "text")
    private String head;
    @Column(name = "group_footer")
    @Type(type = "text")
    private String foot;
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
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
