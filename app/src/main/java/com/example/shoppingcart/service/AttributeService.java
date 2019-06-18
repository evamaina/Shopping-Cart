package com.example.shoppingcart.service;

import com.example.shoppingcart.models.ProductAttribute;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AttributeService {
    @GET("/attributes/inProduct/{product_id}")
    Call<List<ProductAttribute>> getAllAttribute(@Path("product_id") int productId);
}
