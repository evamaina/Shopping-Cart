package com.example.shoppingcart;


import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.adapters.BagDetailAdapter;
import com.example.shoppingcart.adapters.ProductsAdapter;
import com.example.shoppingcart.models.Cart;
import com.example.shoppingcart.models.CartId;
import com.example.shoppingcart.models.Products;
import com.example.shoppingcart.models.Total;
import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.CartService;
import com.example.shoppingcart.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BagFragment extends Fragment {
    BagDetailAdapter bagDetailAdapter;
    RecyclerView bagRecyclerView;
    TextView totalTextView, subTotalTextView, totalItems;
    CartService cartService;
    List<Cart> bagDetailList;
    RecyclerView.LayoutManager manager;
    String cartId;
    String size;
    Button checkOutButton;




    public BagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_bag, container, false);
        totalTextView = view.findViewById(R.id.total);
        subTotalTextView = view.findViewById(R.id.sub_total_cost);
        cartService = ApiUtilities.cartService();
        bagRecyclerView = view.findViewById(R.id.bag_recycler_view);
        checkOutButton = view.findViewById(R.id.checkout_button);
        totalItems = view.findViewById(R.id.total_items);
        cartService = ApiUtilities.cartService();
        bagRecyclerView.setLayoutManager(manager);
        bagRecyclerView.setHasFixedSize(true);
        bagDetailAdapter = new BagDetailAdapter(getContext());
        cartId = Paper.book().read("cartId");
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckoutActivity.class));

            }
        });


        manager = new LinearLayoutManager(getContext());


        bagRecyclerView = view.findViewById(R.id.bag_recycler_view);
        bagRecyclerView.setLayoutManager(manager);
        bagDetailAdapter.setBagDetailList(bagDetailList);
        bagRecyclerView.setAdapter(bagDetailAdapter);
        if (cartId != null) {
            getCartItems(cartId);
            getTotalAmount(cartId);


        }

        return view;
    }


    private void getTotalAmount(String cartId) {
        cartService.getTotalAmount(cartId).enqueue(new Callback<Total>() {
            @Override
            public void onResponse(Call<Total> call, Response<Total> response) {
                if (response.isSuccessful()) {
                    totalTextView.setText(response.body().getTotal());
                    subTotalTextView.setText(response.body().getTotal());




                    return;
                }
                Toast.makeText(getContext(),"error occurred", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Total> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getCartItems(String cartId) {
        cartService.getCartItems(cartId).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    bagDetailAdapter.setBagDetailList(response.body());
                    size = String.valueOf(response.body().size());
                    totalItems.setText(size + " items");


                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
