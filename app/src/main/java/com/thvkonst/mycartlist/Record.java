package com.thvkonst.mycartlist;

import java.io.Serializable;

public class Record implements Serializable {

    public final String title;
    public final String price;
    public String type;

    public Record(String title, String price, String type) {
        this.title = title;
        this.price = price;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}

