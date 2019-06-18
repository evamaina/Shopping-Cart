package com.example.shoppingcart;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shoppingcart.adapters.ProductsAdapter;
import com.example.shoppingcart.models.ProductResponse;
import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.ProductService;
import com.example.shoppingcart.utilities.Constants;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsFragement extends Fragment {
    ProductsAdapter productsAdapter;
    RecyclerView productsRecyclerView;
    ProductService productService;
    RecyclerView.LayoutManager manager;
    SpotsDialog.Builder builder;
    AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products_fragement, container, false);
        productsRecyclerView = view.findViewById(R.id.products_recycler_view);
        manager = new LinearLayoutManager(getContext());
        int categoryId = getArguments().getInt("categoryId");
        productService = ApiUtilities.productService();
        productsAdapter = new ProductsAdapter(getContext());
        builder = new SpotsDialog.Builder().setContext(getContext());
        productsRecyclerView = view.findViewById(R.id.products_recycler_view);
        productsRecyclerView.setAdapter(productsAdapter);
        productsRecyclerView.setLayoutManager(manager);

        if(categoryId==0){
            fetchDpartmentProducts(Constants.currentDepartmentId);
        }else {
            fetchCategoryProducts(categoryId);
        }
        return view;
    }

    private void fetchCategoryProducts(int categoryId) {
        showProgressDialog();
        productService.getCategoryproducts(categoryId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    dismissProgressDialog();
                    productsAdapter.setProductsList(response.body().getProductsList());
                    return;
                }
                dismissProgressDialog();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                dismissProgressDialog();
               Log.w("error",t.getLocalizedMessage());
            }
        });
    }

    private void fetchDpartmentProducts(int currentDepartmentId) {
        showProgressDialog();
        productService.getAllProducts(currentDepartmentId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    dismissProgressDialog();
                    productsAdapter.setProductsList(response.body().getProductsList());
                    return;
                }
                dismissProgressDialog();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                dismissProgressDialog();
                Log.w("error", t.getLocalizedMessage());
            }
        });
    }

    private void dismissProgressDialog() {
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private void showProgressDialog() {
        builder.setMessage("Loading");
        builder.setCancelable(false);
        dialog = builder.build();
        dialog.show();
    }
}
