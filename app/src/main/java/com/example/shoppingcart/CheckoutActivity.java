package com.example.shoppingcart;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shoppingcart.adapters.CheckoutAdapter;
import com.example.shoppingcart.utilities.Constants;
import com.example.shoppingcart.utilities.OnRequestSuccessListener;

import net.skoumal.fragmentback.BackFragmentAppCompatActivity;
import net.skoumal.fragmentback.BackFragmentFragmentActivity;

public class CheckoutActivity extends AppCompatActivity implements OnRequestSuccessListener {
    ViewPager checkOutViewPager;
    CheckoutAdapter checkoutAdapter;
    ImageView paymentPolygon, addressPolygon, completePolygon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        paymentPolygon = findViewById(R.id.payment_polygon);
        addressPolygon = findViewById(R.id.addrress_polygon);
        completePolygon = findViewById(R.id.complete_polygon);
        checkOutViewPager = findViewById(R.id.checkout_view_pager);
        checkOutViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        paymentPolygon.setImageResource(R.drawable.ic_polygon_inactive);
                        addressPolygon.setImageResource(R.drawable.ic_polygon_active);
                        completePolygon.setImageResource(R.drawable.ic_polygon_inactive);
                        break;
                    case 1:
                        paymentPolygon.setImageResource(R.drawable.ic_polygon_active);
                        addressPolygon.setImageResource(R.drawable.ic_polygon_inactive);
                        completePolygon.setImageResource(R.drawable.ic_polygon_inactive);
                        break;
                    case 2:
                        paymentPolygon.setImageResource(R.drawable.ic_polygon_inactive);
                        addressPolygon.setImageResource(R.drawable.ic_polygon_inactive);
                        completePolygon.setImageResource(R.drawable.ic_polygon_active);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        checkoutAdapter = new CheckoutAdapter(getSupportFragmentManager(), this, this);
        checkOutViewPager.setAdapter(checkoutAdapter);

    }


    @Override
    public void onRequestSuccessListener(int positon) {
        checkOutViewPager.setCurrentItem(positon);
        Toast.makeText(this, "lfg", Toast.LENGTH_SHORT).show();
    }
}
