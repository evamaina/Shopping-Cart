package com.example.shoppingcart.service;

import com.example.shoppingcart.models.OrderId;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderService {
    @POST("/orders")
    @FormUrlEncoded
    Call<OrderId> createOrder(@Field("cart_id") String cartId, @Field("shipping_id") int shippingId, @Field("tax_id") int taxId, @Header("USER-KEY") String accessToken);

}
