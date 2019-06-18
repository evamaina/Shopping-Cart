package com.example.shoppingcart.models;

import com.google.gson.annotations.SerializedName;

public class ProductAttribute {
    @SerializedName("attribute_name")
    String attributeName;
    @SerializedName("attribute_value_id")
    int attributeValueId;
    @SerializedName("attribute_value")
    String attributValue;

    public ProductAttribute() {
    }

    public ProductAttribute(String attributeName, int attributeValueId, String attributValue) {
        this.attributeName = attributeName;
        this.attributeValueId = attributeValueId;
        this.attributValue = attributValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getAttributeValueId() {
        return attributeValueId;
    }

    public void setAttributeValueId(int attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    public String getAttributValue() {
        return attributValue;
    }

    public void setAttributValue(String attributValue) {
        this.attributValue = attributValue;
    }
}
