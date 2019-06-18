package com.example.shoppingcart;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.shoppingcart.adapters.CustomSwipeAdapter;

public class SwipeViewPagerActivity extends AppCompatActivity {
    ViewPager swipeViewPager;
    TabLayout swipeTablayout;
    CustomSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_view_pager);
        swipeViewPager = findViewById(R.id.pager);
        adapter = new CustomSwipeAdapter(getSupportFragmentManager());
        swipeViewPager = findViewById(R.id.pager);
        swipeTablayout = findViewById(R.id.product_detail_tab_layout);
        swipeViewPager.setAdapter(adapter);
        swipeTablayout.setupWithViewPager(swipeViewPager);




    }

    public void openSignIn(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
