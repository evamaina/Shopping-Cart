package com.example.shoppingcart.service;

import com.example.shoppingcart.models.ProductResponse;
import com.example.shoppingcart.models.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    @GET("/products/inDepartment/{department_id}")
    Call<ProductResponse> getAllProducts(@Path("department_id") int departmentId);

    @GET("/products/inCategory/{category_id}")
    Call<ProductResponse> getCategoryproducts(@Path("category_id") int categoryId);

    @GET("/products/{product_id}")
    Call<Products> getProductDetail(@Path("product_id") int productId);

}
