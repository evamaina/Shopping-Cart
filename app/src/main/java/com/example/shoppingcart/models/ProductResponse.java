package com.example.shoppingcart.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResponse {
    @SerializedName("rows")
    ArrayList<Products> productsList;

    @SerializedName("count")
    private int total_count;

    public ProductResponse(ArrayList<Products> productsList, int total_count) {
        this.productsList = productsList;
        this.total_count = total_count;
    }

    public ProductResponse() {}

    public ArrayList<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Products> productsList) {
        this.productsList = productsList;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
