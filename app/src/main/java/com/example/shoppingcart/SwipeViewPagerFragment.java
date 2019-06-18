package com.example.shoppingcart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeViewPagerFragment extends Fragment {


    public SwipeViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         ;
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_swipe_view_pager, container, false);
        ImageView imageView = itemView.findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.phone);
        return itemView;
    }


}
