package com.example.shoppingcart.models;

import com.google.gson.annotations.SerializedName;

public class Total {
    @SerializedName("total_amount")
    String total;

    public Total(String total) {
        this.total = total;
    }
    public  Total() {}

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
