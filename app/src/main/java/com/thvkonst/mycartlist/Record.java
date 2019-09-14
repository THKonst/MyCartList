package com.thvkonst.mycartlist;

public class Record {

    private final String title;
    private final double price;
    private String comment;

    public Record(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }
}
