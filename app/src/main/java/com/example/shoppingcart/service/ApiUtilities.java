package com.example.shoppingcart.service;

public class ApiUtilities {

    public ApiUtilities() {
    }

    public static CustomerService customerService(){
        return RetrofitClient.getClient().create(CustomerService.class);
    }

    public static ProductService productService(){
        return RetrofitClient.getClient().create(ProductService.class);
    }

    public static DepartmentsService departmentService(){
        return RetrofitClient.getClient().create(DepartmentsService.class);
    }

    public static CategoriesService categoriesService(){
        return RetrofitClient.getClient().create(CategoriesService.class);
    }

    public static AttributeService attributeService(){
        return RetrofitClient.getClient().create(AttributeService.class);
    }

    public static CartService cartService(){
        return RetrofitClient.getClient().create(CartService.class);
    }

    public static OrderService orderService(){
        return RetrofitClient.getClient().create(OrderService.class);
    }

    public static ShipppingService shipppingService(){
        return RetrofitClient.getClient().create(ShipppingService.class);
    }

    public static StripeService stripeService(){
        return RetrofitClient.getClient().create(StripeService.class);
    }



}
