package com.example.shoppingcart.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_id")
    int categoryId;
    String name;
    String department;
    @SerializedName("department_id")
    int DepartmentId;

    public Category() {
    }

    public Category(int categoryId, String name, String department, int departmentId) {
        this.categoryId = categoryId;
        this.name = name;
        this.department = department;
        DepartmentId = departmentId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }
}
