package com.hm.spinnerdemo.model;

/**
 * Created by Administrator on 2017/2/14.
 */
public class Book {

    private double price;
    private String name;

    public Book(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
