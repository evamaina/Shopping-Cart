package com.example.shoppingcart.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shoppingcart.R;
import com.example.shoppingcart.SwipeViewPagerFragment;

public class CustomSwipeAdapter extends FragmentPagerAdapter {
    View.OnClickListener listener;
    public CustomSwipeAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int i) {
        return new SwipeViewPagerFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }


}
