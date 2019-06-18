package com.example.shoppingcart.service;

import com.example.shoppingcart.models.Cart;
import com.example.shoppingcart.models.CartId;
import com.example.shoppingcart.models.Total;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartService {

    @GET("/shoppingcart/generateUniqueId")
    Call<CartId> getCartId();

    @POST("/shoppingcart/add")
    @FormUrlEncoded
    Call<List<Cart>> addProductToCart(@Field("cart_id") String cartId,
                                      @Field("product_id") int productId,
                                      @Field("attributes") String attributes);

    @GET("/shoppingcart/{cart_id}")
    Call<List<Cart>> getCartItems(@Path("cart_id") String cartId );



    @GET("/shoppingcart/totalAmount/{cart_id}")
    Call<Total> getTotalAmount(@Path("cart_id") String cartId );

}
