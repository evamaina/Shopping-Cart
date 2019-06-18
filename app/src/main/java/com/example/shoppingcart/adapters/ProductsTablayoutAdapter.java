package com.example.shoppingcart.adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shoppingcart.ProductsFragement;
import com.example.shoppingcart.models.Category;

import java.util.List;

public class ProductsTablayoutAdapter extends FragmentStatePagerAdapter {
    List<Category> categoriesList;


    public ProductsTablayoutAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        this.categoriesList = categories;
    }

    @Override
    public Fragment getItem(int i) {
        ProductsFragement fragement = new ProductsFragement();
        Bundle bundle =  new Bundle();
        bundle.putInt("categoryId",categoriesList.get(i).getCategoryId());
        fragement.setArguments(bundle);
        return fragement;
    }

    @Override
    public int getCount() {
        return categoriesList == null ? 0 : categoriesList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categoriesList.get(position).getName();
    }
}
