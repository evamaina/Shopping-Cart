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
import com.example.shoppingcart.utilities.OnRequestSuccessListener;

public class CheckoutAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private  Context context;
    private String[] tabTitles = new String[] { "ADDRESS", "PAYMENT", "COMPLETE"};
    OnRequestSuccessListener onRequestSuccessListener;

    public CheckoutAdapter(FragmentManager fm, Context context, OnRequestSuccessListener onRequestSuccessListener) {
        super(fm);
        this.context = context;
        this.onRequestSuccessListener = onRequestSuccessListener;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            AddressFragment addressFragment = new AddressFragment();
            addressFragment.setOnRequestSuccessListener(onRequestSuccessListener);
            return addressFragment;
        }else if (i == 1) {
            PaymentFragment paymentFragment =  new PaymentFragment();
            paymentFragment.setOnRequestSuccessListener(onRequestSuccessListener);
            return paymentFragment;
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
