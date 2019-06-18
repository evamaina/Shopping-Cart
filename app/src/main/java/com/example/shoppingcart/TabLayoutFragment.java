package com.example.shoppingcart;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingcart.adapters.ContentFragmentPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabLayoutFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager pager;
    private ContentFragmentPagerAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
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



        return view;
    }

}
