package com.example.shoppingcart.service;

import com.example.shoppingcart.models.Shipping;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ShipppingService {

    @GET("/shipping/regions")
    Call<List<Shipping>> getShippingRegion();
}
