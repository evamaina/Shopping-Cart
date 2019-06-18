package com.example.shoppingcart.models;

import com.google.gson.annotations.SerializedName;

public class Shipping {
    @SerializedName("shipping_region_id")
    int ShiipingId;
    @SerializedName("shipping_region")
    String shippingRegion;

    public Shipping(int shiipingId, String shippingRegion) {
        ShiipingId = shiipingId;
        this.shippingRegion = shippingRegion;
    }

    public Shipping() {
    }

    public int getShiipingId() {
        return ShiipingId;
    }

    public void setShiipingId(int shiipingId) {
        ShiipingId = shiipingId;
    }

    public String getShippingRegion() {
        return shippingRegion;
    }

    public void setShippingRegion(String shippingRegion) {
        this.shippingRegion = shippingRegion;
    }
}
