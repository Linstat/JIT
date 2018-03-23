package ru.sivak.addressbookWebTests.model;

public class NewContactParameters {
    private final String first;
    private final String middle;
    private final String last;
    private final String mobile;
    private final String email;
    private String group;

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
