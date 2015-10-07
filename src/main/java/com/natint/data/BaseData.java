package com.natint.data;

/**
 * Created by ivaa on 10/6/2015.
 */
public class BaseData implements IData{

    public String getLink() {
        return link;
    }

    public double getPrice() {
        return price;
    }

    public BaseData(String link, double price) {
        this.link = link;
        this.price = price;
    }

    private String link;
    private double price;
}
