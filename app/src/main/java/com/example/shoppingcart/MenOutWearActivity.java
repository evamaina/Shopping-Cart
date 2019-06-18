//package com.example.shoppingcart;
//
//import android.content.Intent;
//import android.support.design.widget.TabLayout;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.example.shoppingcart.service.ApiUtilities;
//import com.example.shoppingcart.service.CustomerService;
//import com.example.shoppingcart.adapters.ProductsAdapter;
//import com.example.shoppingcart.models.ProductResponse;
//import com.example.shoppingcart.models.Products;
//import com.example.shoppingcart.service.ProductService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MenOutWearActivity extends AppCompatActivity implements MenOutWear {
//    RecyclerView recyclerView;
//    ProductsAdapter adapter;
//    LinearLayoutManager linearLayoutManager;
//    List<Products> productsList;
//    ProductService productService;
//    private TabLayout tabLayout;
//    private ViewPager pager;
//    private ContentFragmentPagerAdapter fragmentAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_men_out_wear);
//
//        productService = ApiUtilities.productService();
//
//        getProducts();
//        recyclerView = findViewById(R.id.my_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        adapter = new ProductsAdapter(this);
//        recyclerView.setAdapter(adapter);
//
//    }
//
//    private void getProducts() {
//        productService
//                .get_products()
//                .enqueue(new Callback<ProductResponse>() {
//                    @Override
//                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
//                        if (response.isSuccessful()) {
//                            ArrayList<Products> productsList = response.body().getProductsList();
//                            adapter.setProductsList(productsList);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ProductResponse> call, Throwable t) {
//                        Toast.makeText(MenOutWearActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//}
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
//        pager = view.findViewById(R.id.view_pager);
//        fragmentAdapter = new ContentFragmentPagerAdapter(getSupportFragmentManager(),this);
//        pager.setAdapter(fragmentAdapter);
//
//        // Give the TabLayout the ViewPager
//        tabLayout = view.findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(pager);
//        for (int i = 0; i<tabLayout.getTabCount();i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            View tabView = fragmentAdapter.getTab(i);
//            tab.setCustomView(tabView);
//        }
//
//        return view;
//    }
//
//
//}
