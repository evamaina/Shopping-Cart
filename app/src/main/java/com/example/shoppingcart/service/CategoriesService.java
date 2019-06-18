package com.example.shoppingcart.service;

import com.example.shoppingcart.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriesService {
    @GET("/categories/inDepartment/{department_id}")
    Call<List<Category>> getAllCategory(@Path("department_id") int id);

}
