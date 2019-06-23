package com.example.shoppingcart;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shoppingcart.adapters.ContentFragmentPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabLayoutFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager pager;
    private ContentFragmentPagerAdapter adapter;
    View.OnClickListener onClickListener;
    ImageView navImage, bagImage;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        navImage = view.findViewById(R.id.nav_button);
        bagImage = view.findViewById(R.id.bag_nav_btn);
        pager = view.findViewById(R.id.view_pager);
        adapter = new ContentFragmentPagerAdapter(getActivity().getSupportFragmentManager(),getContext());
        pager.setAdapter(adapter);


        // Give the TabLayout the ViewPager
        tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(pager);
        for (int i = 0; i<tabLayout.getTabCount();i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View tabView = adapter.getTab(i);
            tab.setCustomView(tabView);
        }
        navImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });

        return view;
    }

}
