package com.example.shoppingcart.service;

import com.example.shoppingcart.models.CustomerResponse;
import com.example.shoppingcart.models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CustomerService {

    @POST("/customers")
    @FormUrlEncoded
    Call<CustomerResponse> registerCustomer(@Field("name") String name,@Field("email") String email, @Field("password") String password);

    @POST("/customers/login")
    @FormUrlEncoded
    Call<CustomerResponse> loginCustomer(@Field("email") String email,@Field("password") String password);

    @PUT("/customers/address")
    @FormUrlEncoded
    Call<CustomerResponse> updateCustomerAddress(@Field("name") String name,
                                                 @Field("address_1") String address1,
                                                 @Field("address_2") String address2,
                                                 @Field("city") String city,
                                                 @Field("region") String state,
                                                 @Field("postal_code") String zipCode,
                                                 @Field("country") String country,
                                                 @Field("shipping_region_id") int shippingId,
                                                 @Header("USER-KEY") String token)
            ;



}
