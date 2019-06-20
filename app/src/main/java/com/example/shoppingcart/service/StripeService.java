package com.example.shoppingcart.service;

import com.stripe.android.model.Card;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StripeService {
    @POST("/stripe/charge")
    @FormUrlEncoded
    Call<Card> postCharges(
            @Field("stripeToken") String stripeToken,
            @Field("order_id") int orderId,
            @Field("description") String description,
            @Field("amount") int amount,
            @Field("currency") String currency);
}
