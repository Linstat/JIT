package ru.sivak.addressbookWebTests.model;

import java.util.Objects;

public class NewContactParameters {
    private final String first;
    private final String last;
    private final String mobile;
    private final String email;
    private String group;
    private int id;

    public NewContactParameters(String first, String last, String mobile, String email, String group) {
        this.first = first;
        this.last = last;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
        this.id = Integer.MAX_VALUE;
    }

    public NewContactParameters(int id, String first, String last, String mobile, String email, String group) {
        this.first = first;
        this.last = last;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getMobile() {
        return mobile;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "NewContactParameters{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", mobile='" + mobile + '\'' +
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
        boolean lastResult = checkResult(last, that.last);
        boolean mobileResult = checkResult(mobile, that.mobile);
        boolean emailResult = checkResult(email, that.email);
        boolean firstResult = checkResult(first, that.first);
        boolean groupResult = checkResult(group, that.group);
        return firstResult &&
                lastResult &&
                mobileResult &&
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

        return Objects.hash(first, last, mobile, email, group);
    }
}
