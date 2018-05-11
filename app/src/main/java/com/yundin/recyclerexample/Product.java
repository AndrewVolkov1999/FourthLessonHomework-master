package com.yundin.recyclerexample;

import com.google.gson.annotations.SerializedName;

/**
 * @author Yundin Vladislav
 */
public class Product {

    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private String imageUrl;
    @SerializedName("price")
    private int price;
    @SerializedName("title")
    private String title;

    public Product(int id, String imageUrl, int price, String title) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.price = price;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
