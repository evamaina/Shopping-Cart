package com.example.shoppingcart;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.skoumal.fragmentback.BackFragmentAppCompatActivity;

public class HomeActivity extends BackFragmentAppCompatActivity {
    SlidingPaneLayout pane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pane =  findViewById(R.id.sliding_pane);
        pane.setPanelSlideListener(new PaneListener());

    }


    }






