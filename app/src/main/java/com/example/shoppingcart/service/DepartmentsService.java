package com.example.shoppingcart.service;

import com.example.shoppingcart.models.Department;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DepartmentsService {

    @GET("/departments")
    Call<List<Department>> getAllDepartments();
}
