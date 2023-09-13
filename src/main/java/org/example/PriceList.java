package org.example;

import java.util.List;

public class PriceList {
    private String hour;
    private int price;

    public PriceList(String hour, int price) {
        this.hour = hour;
        this.price = price;
    }

    public String getHour() {
        return hour;
    }

    public int getPrice() {
        return price;
    }
}
