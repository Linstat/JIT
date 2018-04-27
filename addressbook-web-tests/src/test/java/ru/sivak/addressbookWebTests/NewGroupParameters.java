package ru.sivak.addressbookWebTests;

public class NewGroupParameters {
    private final String name;
    private final String head;
    private final String foot;

    public NewGroupParameters(String name, String head, String foot) {
        this.name = name;
        this.head = head;
        this.foot = foot;
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
}
