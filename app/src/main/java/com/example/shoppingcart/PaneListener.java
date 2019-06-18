package com.example.shoppingcart;

import android.support.annotation.NonNull;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;

public class PaneListener implements SlidingPaneLayout.PanelSlideListener {


    @Override
    public void onPanelClosed(View view) {
        System.out.println("Panel closed");

    }

    @Override
    public void onPanelOpened(View view) {
        System.out.println("Panel opened");
    }

    @Override
    public void onPanelSlide(View view, float arg1) {
        System.out.println("Panel sliding");
    }
}
