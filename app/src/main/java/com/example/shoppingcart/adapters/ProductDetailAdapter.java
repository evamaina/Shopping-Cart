package com.example.shoppingcart.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shoppingcart.ProductDetailFragment;

public class ProductDetailAdapter extends FragmentPagerAdapter {
    public ProductDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new ProductDetailFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
