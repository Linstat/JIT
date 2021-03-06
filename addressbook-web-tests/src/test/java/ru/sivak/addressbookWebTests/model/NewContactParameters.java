package ru.sivak.addressbookWebTests.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class NewContactParameters {
    @Column(name = "firstname")
    private String first;
    @Column(name = "lastname")
    private String last;
    @Column(name = "email")
    @Type(type = "text")
    private String email1;
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Column(name = "home")
    @Type(type = "text")
    private String home;
    @Column(name = "work")
    @Type(type = "text")
    private String work;
    @Transient
    private String allEmails;
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @Transient
    private String allPhones;
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<NewGroupParameters> groups = new HashSet<NewGroupParameters>();

    public Groups getGroups() {
        return new Groups(groups);
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public int getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHome() {
        return home;
    }

    public String getWork() {
        return work;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "NewContactParameters{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", group='" + groups + '\'' +
                ", mobile='" + mobile + '\'' +
                ", home='" + home + '\'' +
                ", work='" + work + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", id=" + id +
                '}';
    }

    public File getPhoto() {
        return new File(photo);
    }

    public NewContactParameters withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public NewContactParameters withAddress(String address) {
        this.address = address;
        return this;
    }

    public NewContactParameters withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public NewContactParameters withWork(String work) {
        this.work = work;
        return this;
    }

    public NewContactParameters withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public NewContactParameters withHome(String home) {
        this.home = home;
        return this;
    }

    public NewContactParameters withFirst(String first) {
        this.first = first;
        return this;
    }

    public NewContactParameters withLast(String last) {
        this.last = last;
        return this;
    }

    public NewContactParameters withAllEmails(String AllEmails) {
        this.allEmails = AllEmails;
        return this;
    }

    public NewContactParameters withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public NewContactParameters withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public NewContactParameters withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public NewContactParameters withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewContactParameters that = (NewContactParameters) o;
        boolean idResult = checkResult(Integer.toString(id), Integer.toString(that.id));
        boolean lastResult = checkResult(last, that.last);
        boolean firstResult = checkResult(first, that.first);
        boolean email1Result = checkResult(email1, that.email1);
        return firstResult &&
                idResult &&
                lastResult &&
                email1Result;
        }

    private boolean checkResult(String param1, String param2) {
        if (param1 == null || param2 == null) {
            return true;
        } else {
            return Objects.equals(param1, param2);
        }
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, last, groups);
    }

    public NewContactParameters inGroup(NewGroupParameters group) {
        groups.add(group);
        return this;
    }
}
