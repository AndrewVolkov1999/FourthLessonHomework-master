package com.yundin.recyclerexample.api;

/**
 * @author Yundin Vladislav
 */
public class AddProductRequest {

    private String title;
    private int price;
    private String image;

    public AddProductRequest(String title, int price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }
}
