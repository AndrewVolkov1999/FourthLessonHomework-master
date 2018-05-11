package com.yundin.recyclerexample.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Yundin Vladislav
 */
public interface ApiInterface {

    @GET("goods")
    Call<GetAllGoodsResponse> getAllGoods();

    @Headers("Content-Type: application/json")
    @POST("goods/add/")
    Call<AddProductResponse> addProduct(@Body AddProductRequest request);
}
