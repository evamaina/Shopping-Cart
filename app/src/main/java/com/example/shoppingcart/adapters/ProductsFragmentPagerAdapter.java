//package com.example.shoppingcart.adapters;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//import com.example.shoppingcart.AllProductsFragment;
//import com.example.shoppingcart.R;
//
//public class ProductsFragmentPagerAdapter extends FragmentPagerAdapter {
//    final int PAGE_COUNT = 5;
////    private String[] tabTitles = new String[] { "All Products", "Jeans", "T-Shirts","Jeans", "Accessories" };
//    private Context context;
//
//    public ProductsFragmentPagerAdapter(FragmentManager fm, Context context) {
//        super(fm);
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return PAGE_COUNT;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//        return false;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        if (position == 0) {
//            return new AllProductsFragment();
//        }else {
//            return new AllProductsFragment();
//        }
//
//
//    }
//
//    public View getTab(int position) {
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab,null);
//        TextView tabText =view.findViewById(R.id.product_text_view);
////        tabText.setText(tabTitles[position]);
//        return view;
//
//    }
//
//}
