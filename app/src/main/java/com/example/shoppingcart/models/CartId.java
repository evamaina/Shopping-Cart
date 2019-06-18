package com.example.shoppingcart.models;

import com.google.gson.annotations.SerializedName;

public class CartId {
    @SerializedName("cart_id")
    String cartId;

    public CartId() {
    }

    public CartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
