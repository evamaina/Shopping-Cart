package com.example.shoppingcart.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.AddressFragment;
import com.example.shoppingcart.BackFragment;
import com.example.shoppingcart.PaymentFragment;
import com.example.shoppingcart.R;

public class CheckoutAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private  Context context;
    private String[] tabTitles = new String[] { "ADDRESS", "PAYMENT", "COMPLETE"};

    public CheckoutAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return tabTitles[position];
//    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return new AddressFragment();
        }else if (i == 1) {
            return new PaymentFragment();
        }else {
            return new BackFragment();
        }


    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public View getTab(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkout_tab,null);
        TextView tabText =view.findViewById(R.id.checkout_text_view);
        tabText.setText(tabTitles[position]);
        return view;

    }
}
