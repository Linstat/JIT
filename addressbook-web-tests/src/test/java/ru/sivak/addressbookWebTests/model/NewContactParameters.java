package ru.sivak.addressbookWebTests.model;

import java.io.File;
import java.util.Objects;

public class NewContactParameters {
    private String first;
    private String last;
    private String email1;
    private String email2;
    private String email3;
    private String group;
    private String mobile;
    private String home;
    private String work;
    private String allEmails;
    private String address;
    private File photo;




    private String allPhones;
    private int id = Integer.MAX_VALUE;

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

    public String getGroup() {
        return group;
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

    public File getPhoto() {
        return photo;
    }

    public NewContactParameters withPhoto(File photo) {
        this.photo = photo;
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

    public NewContactParameters withGroup(String group) {
        this.group = group;
        return this;
    }

    public NewContactParameters withId (int id){
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "NewContactParameters{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email1='" + email1 + '\'' +
                ", group='" + group + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewContactParameters that = (NewContactParameters) o;
        boolean idResult = checkResult(Integer.toString(id), Integer.toString(that.id));
        boolean lastResult = checkResult(last, that.last);
        boolean firstResult = checkResult(first, that.first);
        boolean groupResult = checkResult(group, that.group);
        return firstResult &&
                idResult &&
                lastResult &&
                groupResult;
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

        return Objects.hash(first, last, group);
    }
}
