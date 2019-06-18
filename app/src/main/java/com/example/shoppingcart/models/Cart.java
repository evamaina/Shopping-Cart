package com.example.shoppingcart.models;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("item_id")
    int itemId;
    String name;
    String attributes;
    int quantity;
    @SerializedName("product_id")
    int productId;
    String subtotal;
    String price;

    public Cart() {
    }

    public Cart(int itemId, String name, String attributes, int quantity, int productId, String subtotal, String price) {
        this.itemId = itemId;
        this.name = name;
        this.attributes = attributes;
        this.quantity = quantity;
        this.productId = productId;
        this.subtotal = subtotal;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
