package ru.sivak.addressbookWebTests.model;

public class NewContactParameters {
    private final String first;
    private final String middle;
    private final String last;
    private final String mobile;
    private final String email;

    public NewContactParameters(String first, String middle, String last, String mobile, String email) {
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.mobile = mobile;
        this.email = email;
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
}
