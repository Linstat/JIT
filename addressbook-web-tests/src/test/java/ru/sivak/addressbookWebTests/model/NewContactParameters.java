package ru.sivak.addressbookWebTests.model;

import java.util.Objects;

public class NewContactParameters {
    private String first;
    private String last;
    private String email;
    private String group;
    private String mobile;
    private String home;
    private String work;
    private int id = Integer.MAX_VALUE;

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getEmail() {
        return email;
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

    public NewContactParameters withEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
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
        boolean emailResult = checkResult(email, that.email);
        boolean firstResult = checkResult(first, that.first);
        boolean groupResult = checkResult(group, that.group);
        return firstResult &&
                idResult &&
                lastResult &&
                emailResult &&
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

        return Objects.hash(first, last, email, group);
    }

}
