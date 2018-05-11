package com.yundin.recyclerexample.api;

import com.google.gson.annotations.SerializedName;
import com.yundin.recyclerexample.Product;

/**
 * @author Yundin Vladislav
 */
public class AddProductResponse {

    @SerializedName("product")
    private Product data;

    public Product getData() {
        return data;
    }
}
