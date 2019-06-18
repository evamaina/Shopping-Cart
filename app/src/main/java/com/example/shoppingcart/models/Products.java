package com.example.shoppingcart.models;

public class Products {
    private int product_id;
    private String name;
    private String description;
    private String price;
    private String discounted_price;
    private String thumbnail;

    public Products(int product_id, String name, String description, String price, String discounted_price, String thumbnail) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discounted_price = discounted_price;
        this.thumbnail = thumbnail;
    }

    public Products() {

    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscounted_price() {
        return discounted_price;
    }

    public void setDiscounted_price(String discounted_price) {
        this.discounted_price = discounted_price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}



