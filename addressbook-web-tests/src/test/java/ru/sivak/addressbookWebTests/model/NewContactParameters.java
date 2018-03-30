package ru.sivak.addressbookWebTests.model;

import java.util.Objects;

public class NewContactParameters {
    private final String first;
    private final String middle;
    private final String last;
    private final String mobile;
    private final String email;

    private String group;

    @Override
    public String toString() {
        return "NewContactParameters{" +
                "first='" + first + '\'' +
                ", middle='" + middle + '\'' +
                ", last='" + last + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewContactParameters that = (NewContactParameters) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(middle, that.middle) &&
                Objects.equals(last, that.last) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {

        return Objects.hash(first, middle, last, mobile, email, group);
    }

    public NewContactParameters(String first, String middle, String last, String mobile, String email, String group) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public String getFirst() {
        return first;
    }

    public String getMiddle() {
        return middle;
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
}
