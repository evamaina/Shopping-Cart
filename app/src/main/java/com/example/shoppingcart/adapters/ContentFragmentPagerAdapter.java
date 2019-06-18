package com.example.shoppingcart.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingcart.BagFragment;
import com.example.shoppingcart.ContentFragment;
import com.example.shoppingcart.R;

public class ContentFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String[] tabTitles = new String[] { "Shop", "Inspiration", "Stores","More" };
    private int[] tabImages = new int[] { R.drawable.ic_shop_icon,R.drawable.ic_inspiration_icon,
             R.drawable.ic_stores_icon, R.drawable.ic_more_icon};
    private Context context;

    public ContentFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 2) {
            return new BagFragment();
        }
        else {
            return new ContentFragment();
        }


    }

    public View getTab(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab,null);
        ImageView tabImage = view.findViewById(R.id.imagee);
        TextView tabText =view.findViewById(R.id.text_view);
        tabImage.setImageResource(tabImages[position]);
        tabText.setText(tabTitles[position]);
        return view;

    }


}
