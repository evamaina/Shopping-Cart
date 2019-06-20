package com.example.shoppingcart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingcart.models.CustomerResponse;
import com.example.shoppingcart.models.OrderId;
import com.example.shoppingcart.models.Shipping;
import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.CustomerService;
import com.example.shoppingcart.service.OrderService;
import com.example.shoppingcart.service.ShipppingService;
import com.example.shoppingcart.utilities.Constants;
import com.example.shoppingcart.utilities.OnRequestSuccessListener;
import com.jaredrummler.materialspinner.MaterialSpinner;

import net.skoumal.fragmentback.BackFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {
    CustomerService customerService;
    OrderService orderService;
    Button saveContinue;
    EditText firstName, lastName, address1, address2, city, state, zipcode, country;
    String access_token, cartId;
    ShipppingService shipppingService;
    MaterialSpinner materialSpinner;

    OnRequestSuccessListener onRequestSuccessListener;

    public void setOnRequestSuccessListener(OnRequestSuccessListener onRequestSuccessListener) {
        this.onRequestSuccessListener = onRequestSuccessListener;
    }

    public AddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);
        customerService = ApiUtilities.customerService();
        orderService = ApiUtilities.orderService();
        saveContinue = view.findViewById(R.id.save_continue);
        address1 = view.findViewById(R.id.address_one);
        address2 = view.findViewById(R.id.address_two);
        city = view.findViewById(R.id.city);
        state = view.findViewById(R.id.state);
        materialSpinner = view.findViewById(R.id.material_spinner);
        zipcode = view.findViewById(R.id.zip_code);
        shipppingService = ApiUtilities.shipppingService();
        getShippingRegion();
        Paper.init(getActivity());
        access_token = Paper.book().read("accessToken");
        cartId = Paper.book().read("cartId");
        country = view.findViewById(R.id.country);
        firstName = view.findViewById(R.id.f_name);
        lastName = view.findViewById(R.id.l_name);
        saveContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = firstName.getText().toString() + " " + lastName.getText().toString();
                if (access_token != null) {
                    updateCustomerAddress( address1.getText().toString(),
                            address2.getText().toString(),
                            city.getText().toString(),
                            state.getText().toString(),
                            zipcode.getText().toString(),
                            country.getText().toString(), 1, access_token);
                }



            }
        });
        return view;
    }

    private void getShippingRegion() {
        shipppingService.getShippingRegion().enqueue(new Callback<List<Shipping>>() {
            @Override
            public void onResponse(Call<List<Shipping>> call, Response<List<Shipping>> response) {
                if(response.isSuccessful()){
                    List<String> regions =  new ArrayList<>();
                    for(Shipping region:response.body()){
                        regions.add(region.getShippingRegion());
                    }
                    materialSpinner.setItems(regions);
                    return;
                }
                Toast.makeText(getContext(), "An error occured", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Shipping>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCustomerAddress( String address1, String address2, String city, String state, String zipCode, String country, int shippingId, final String access_token) {
        customerService.updateCustomerAddress(address1,
                address2, city, state, zipCode, country,shippingId, access_token).enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    createOrder(cartId, 1, 1, access_token);
                    onRequestSuccessListener.onRequestSuccessListener(1);
                    Toast.makeText(getContext(), "order created", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void createOrder(String cartId, int shippingId, int taxId, String access_token) {
        orderService.createOrder(cartId, shippingId, taxId, access_token).enqueue(new Callback<OrderId>() {
            @Override
            public void onResponse(Call<OrderId> call, Response<OrderId> response) {
                if (response.isSuccessful()) {
                    int orderId = response.body().getOrderId();
                    Paper.book().write("orderId", orderId);
                    return;
                }
                Toast.makeText(getContext(),"error occured", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<OrderId> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
