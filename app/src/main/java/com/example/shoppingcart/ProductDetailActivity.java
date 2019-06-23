package com.example.shoppingcart;

import android.app.AlertDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.adapters.ColorAdapter;
import com.example.shoppingcart.adapters.ProductDetailAdapter;
import com.example.shoppingcart.adapters.SizeAdapter;
import com.example.shoppingcart.models.Cart;
import com.example.shoppingcart.models.ProductAttribute;
import com.example.shoppingcart.models.Products;
import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.AttributeService;
import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.ProductService;
import com.example.shoppingcart.utilities.OnAttributeClickListener;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, OnAttributeClickListener {
    ProductDetailAdapter adapter;
    ViewPager productDetailViewPage;
    TextView price, discountedPrice, description, productName;
    TabLayout productViewPagerLayout;
    ProductService productService;
    LinearLayout attributeLayout, customizeLayout;
    RecyclerView colorAttributes, sizeAttribute;
    List<ProductAttribute> colorAttributeList, sizeAttributes;
    AttributeService attributeService;
    ColorAdapter colorAdapter;
    SizeAdapter sizeAdapter;
    StringBuilder attributes;
    ImageView closeBtn;
    RecyclerView.LayoutManager colorManager, sizeManager;
    CartService cartService;
    Button addToCart;
    String cartId;
    String productId;
    AlertDialog dialog;
    SpotsDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        adapter = new ProductDetailAdapter(getSupportFragmentManager());
        productId = getIntent().getStringExtra("productId");
        price = findViewById(R.id.price);
        discountedPrice = findViewById(R.id.discounted_price);
        description = findViewById(R.id.product_description_text);
        colorAttributes = findViewById(R.id.color_list_view);
        attributeLayout = findViewById(R.id.attribute_view);
        sizeAttribute = findViewById(R.id.size_list_view);
        productName = findViewById(R.id.product_name);
        closeBtn = findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(this);
        attributeService = ApiUtilities.attributeService();
        colorManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        sizeManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        productDetailViewPage = findViewById(R.id.product_detail_viewpager);
        addToCart = findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(this);
        productService = ApiUtilities.productService();
        builder = new SpotsDialog.Builder().setContext(this);
        cartService = ApiUtilities.cartService();
        customizeLayout = findViewById(R.id.customize_container);
        customizeLayout.setOnClickListener(this);
        productDetailViewPage.setAdapter(adapter);
        sizeAttribute.setLayoutManager(sizeManager);
        sizeAttribute.setHasFixedSize(true);
        colorAttributes.setLayoutManager(colorManager);
        colorAttributes.setHasFixedSize(true);
        colorAdapter = new ColorAdapter(this, this);
        sizeAdapter = new SizeAdapter(this, this);
        colorAttributes.setAdapter(colorAdapter);
        sizeAttribute.setAdapter(sizeAdapter);
        colorAttributeList = new ArrayList<>();
        sizeAttributes = new ArrayList<>();
        attributes = new StringBuilder();
        productViewPagerLayout = findViewById(R.id.product_tablayout);
        productViewPagerLayout.setupWithViewPager(productDetailViewPage);
        if(productId!=null){
            getProductDetail(productId);
        getAttributesDetails(Integer.parseInt(productId));
        }
        cartId = Paper.book().read("cartId");
    }

    private void getProductDetail(String productId) {
        showProgressDialog();
        productService.getProductDetail(Integer.parseInt(productId)).enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    dismissProgressDialog();
                    price.setText("$" + response.body().getPrice());
                    price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    discountedPrice.setText("$" + response.body().getDiscounted_price());
                    description.setText(response.body().getDescription());
                    productName.setText(response.body().getName());
                    return;

                }
                dismissProgressDialog();
                Log.i("error", "an error occurred");
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                dismissProgressDialog();
                Log.i("error", "an error occurred");

            }
        });
    }

    private void getAttributesDetails(int parseInt) {
        attributeService.getAllAttribute(parseInt).enqueue(new Callback<List<ProductAttribute>>() {
            @Override
            public void onResponse(Call<List<ProductAttribute>> call, Response<List<ProductAttribute>> response) {
                if (response.isSuccessful()){
                    for(ProductAttribute productAttribute:response.body()){
                        if(productAttribute.getAttributeName().equals("Color")){
                            colorAttributeList.add(productAttribute);

                        }else {
                            sizeAttributes.add(productAttribute);
                        }
                    }
                    colorAdapter.setProductAttributeList(colorAttributeList);
                    sizeAdapter.setAttributeList(sizeAttributes);
                    return;
                }
                Toast.makeText(ProductDetailActivity.this, "an error occurred", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<ProductAttribute>> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==customizeLayout){
            if(attributeLayout.getVisibility()==View.VISIBLE){
                attributeLayout.setVisibility(View.GONE);
            }
            else {
                attributeLayout.setVisibility(View.VISIBLE);
            }
        }
        else if(v==addToCart){
            if(attributes.length()>0 && cartId!=null && productId!=null){
                addProductToCart();
            }
            else {
                Toast.makeText(this, "Please add an attribute", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v==closeBtn) {
            finish();
        }
    }

    private void addProductToCart() {
        showProgressDialog();
        cartService.addProductToCart(cartId, Integer.parseInt(productId), attributes.toString()).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if(response.isSuccessful()){
                    dismissProgressDialog();
                    Toast.makeText(ProductDetailActivity.this, "Added", Toast.LENGTH_SHORT).show();
                    return;
                }
                dismissProgressDialog();
                Toast.makeText(ProductDetailActivity.this, "Not added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(ProductDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onAttributeClickListener(String attribute) {
        Toast.makeText(this, attribute, Toast.LENGTH_SHORT).show();
        attributes.append(attribute);
    }

}
