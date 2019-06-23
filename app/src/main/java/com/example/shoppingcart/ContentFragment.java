package com.example.shoppingcart;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.adapters.ProductsTablayoutAdapter;
import com.example.shoppingcart.models.CartId;
import com.example.shoppingcart.models.Category;
import com.example.shoppingcart.models.Department;
import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.CategoriesService;
import com.example.shoppingcart.service.DepartmentsService;
import com.example.shoppingcart.utilities.Constants;

import net.skoumal.fragmentback.BackFragment;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment implements View.OnClickListener, BackFragment {

    LinearLayout menLayout, womenLayout;
    RelativeLayout offerLayout, productView;
    TextView menTextview, womenTextview, mendescrption, womenDescription, witerSaleText, winterSaleTextDescription;
    DepartmentsService departmentsService;
    TabLayout productsTabLayout;
    ViewPager categoriesViewPager;
    Department regional, natural, seasonal;
    CategoriesService categoriesService;
    CartService cartService;
    ProductsTablayoutAdapter productsTablayoutAdapter;
    AlertDialog dialog;
    SpotsDialog.Builder builder;

    public ContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        menLayout = view.findViewById(R.id.men_layout);
        womenLayout = view.findViewById(R.id.women_layout);
        offerLayout = view.findViewById(R.id.offer_view);
        menTextview = view.findViewById(R.id.men_text_view);
        womenTextview = view.findViewById(R.id.women_text);
        mendescrption = view.findViewById(R.id.men_text_view_description);
        womenDescription = view.findViewById(R.id.women_text_view_description);
        winterSaleTextDescription = view.findViewById(R.id.winter_sale_text_description);
        witerSaleText = view.findViewById(R.id.winter_sale_text);
        builder = new SpotsDialog.Builder().setContext(getActivity());
        cartService = ApiUtilities.cartService();

        Paper.init(getActivity());
        String cartId = Paper.book().read("cartId");
        if(cartId==null){
            getCartId();
        }
        productView = view.findViewById(R.id.products_view);
        productsTabLayout = view.findViewById(R.id.categories_tab_layout);
        categoriesViewPager = view.findViewById(R.id.products_view_pager);
        categoriesService = ApiUtilities.categoriesService();
        departmentsService = ApiUtilities.departmentService();
        menLayout.setOnClickListener(this);
        womenLayout.setOnClickListener(this);
        offerLayout.setOnClickListener(this);
        fetchallProducts();
        return view;
    }

    private void getCartId() {
        cartService.getCartId().enqueue(new Callback<CartId>() {
            @Override
            public void onResponse(Call<CartId> call, Response<CartId> response) {
                if(response.isSuccessful()){
                    String cartId = response.body().getCartId();
                    Paper.book().write("cartId", cartId);
                    return;
                }
                Toast.makeText(getContext(), "An error occurred when fetching CartId", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CartId> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchallProducts() {
        showProgressDialog();
        departmentsService.getAllDepartments().enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if(response.isSuccessful()){
                    dismissProgressDialog();
                    regional = response.body().get(0);
                    natural = response.body().get(1);
                    seasonal = response.body().get(2);
                    menTextview.setText(regional.getName());
                    womenTextview.setText(natural.getName());
                    witerSaleText.setText(seasonal.getName());
                    winterSaleTextDescription.setText(seasonal.getDescription());
                    mendescrption.setText(regional.getDescription());
                    womenDescription.setText(natural.getDescription());
                    return;
                }
                dismissProgressDialog();
                Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==menLayout){
            productView.setVisibility(View.VISIBLE);
            womenLayout.setVisibility(View.GONE);
            offerLayout.setVisibility(View.GONE);
            Constants.currentDepartmentId = regional.getDepartmentId();
            getAllCategories(Constants.currentDepartmentId);
        }
        else if(v==womenLayout){
            productView.setVisibility(View.VISIBLE);
            menLayout.setVisibility(View.GONE);
            offerLayout.setVisibility(View.GONE);
            Constants.currentDepartmentId = natural.getDepartmentId();
            getAllCategories(Constants.currentDepartmentId);
        }
        else if(v==offerLayout){
            productView.setVisibility(View.VISIBLE);
            womenLayout.setVisibility(View.GONE);
            menLayout.setVisibility(View.GONE);
            Constants.currentDepartmentId = seasonal.getDepartmentId();
            getAllCategories(Constants.currentDepartmentId);
        }
    }

    private void getAllCategories(int currentDepartmentId) {
        showProgressDialog();
        categoriesService.getAllCategory(currentDepartmentId).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    dismissProgressDialog();
                    Category category = new Category();
                    category.setName("All Product");
                    category.setDepartmentId(0);
                    response.body().add(0,category);
                    productsTablayoutAdapter = new ProductsTablayoutAdapter(getChildFragmentManager(),response.body());
                    categoriesViewPager.setAdapter(productsTablayoutAdapter);
                    productsTabLayout.setupWithViewPager(categoriesViewPager);
                    return;
                }
                dismissProgressDialog();
                Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onBackPressed() {
        productView.setVisibility(View.GONE);
        menLayout.setVisibility(View.VISIBLE);
        womenLayout.setVisibility(View.VISIBLE);
        offerLayout.setVisibility(View.VISIBLE);
        return true;
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

    @Override
    public int getBackPriority() {
        return 0;
    }
}

