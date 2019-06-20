package com.example.shoppingcart;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shoppingcart.adapters.CheckoutAdapter;
import com.example.shoppingcart.utilities.Constants;
import com.example.shoppingcart.utilities.OnRequestSuccessListener;

import net.skoumal.fragmentback.BackFragmentAppCompatActivity;
import net.skoumal.fragmentback.BackFragmentFragmentActivity;

public class CheckoutActivity extends AppCompatActivity implements OnRequestSuccessListener {
    ViewPager checkOutViewPager;
    TabLayout checkOutTabLayout;
    CheckoutAdapter checkoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        checkOutViewPager = findViewById(R.id.checkout_view_pager);
        checkOutTabLayout = findViewById(R.id.checkout_tablayout);
        checkoutAdapter = new CheckoutAdapter(getSupportFragmentManager(), this, this);

        checkOutViewPager.setAdapter(checkoutAdapter);
        checkOutTabLayout.setupWithViewPager(checkOutViewPager);

        for (int i = 0; i<checkOutTabLayout.getTabCount();i++) {
            TabLayout.Tab tab = checkOutTabLayout.getTabAt(i);
            View tabView = checkoutAdapter.getTab(i);
            checkOutTabLayout.getTabAt(i).setCustomView(tabView);
        }

    }


    @Override
    public void onRequestSuccessListener(int positon) {
        checkOutViewPager.setCurrentItem(positon);
        Toast.makeText(this, "lfg", Toast.LENGTH_SHORT).show();
    }
}
