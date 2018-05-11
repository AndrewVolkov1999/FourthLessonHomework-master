package com.yundin.recyclerexample.api;

import com.google.gson.annotations.SerializedName;
import com.yundin.recyclerexample.Product;

import java.util.List;

/**
 * @author Yundin Vladislav
 */
public class GetAllGoodsResponse {

    @SerializedName("goods")
    private List<Product> data;

    public List<Product> getData() {
        return data;
    }
}
