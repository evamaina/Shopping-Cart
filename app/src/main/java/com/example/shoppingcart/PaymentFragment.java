package com.example.shoppingcart;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.models.Customer;
import com.example.shoppingcart.models.CustomerResponse;
import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.CustomerService;
import com.example.shoppingcart.service.StripeService;
import com.example.shoppingcart.utilities.OnRequestSuccessListener;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;

import java.io.IOException;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    TextView paymentAddressOneTextView, paymentCityTextView, paymentRegionTextView, paymentCountryTextView, paymentPostalCogeTextView;
    EditText cardNumbeEditText, yearEditText, monthEditText, cvcEditText;
    Button payButton;
    CustomerService customerService;
    OnRequestSuccessListener onRequestSuccessListener;
    Stripe stripe;
    StripeService stripeService;

    public void setOnRequestSuccessListener(OnRequestSuccessListener onRequestSuccessListener) {
        this.onRequestSuccessListener = onRequestSuccessListener;
    }

    Card card;
    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        Paper.init(getContext());
        String accessToken = Paper.book().read("accessToken");
        payButton = view.findViewById(R.id.pay_button);
        stripe = new Stripe(getContext(), getString(R.string.stripe_token));
        paymentAddressOneTextView = view.findViewById(R.id.address_one_payment);
        paymentCityTextView = view.findViewById(R.id.state_text);
        paymentRegionTextView = view.findViewById(R.id.region_text);
        paymentCountryTextView = view.findViewById(R.id.country_text);
        paymentPostalCogeTextView = view.findViewById(R.id.postal_code_payment);
        cardNumbeEditText = view.findViewById(R.id.card_number);
        yearEditText = view.findViewById(R.id.card_year_input);
        monthEditText = view.findViewById(R.id.card_month_input);
        cvcEditText = view.findViewById(R.id.cvc_card_number);
        customerService = ApiUtilities.customerService();
        stripeService = ApiUtilities.stripeService();
        getCustomerShippingInfo(accessToken);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String month = monthEditText.getText().toString();
                String year = yearEditText.getText().toString();
                String cardNumber = cardNumbeEditText.getText().toString();
                String cvcCode = cvcEditText.getText().toString();
                card = Card.create(cardNumber,Integer.parseInt(month), Integer.parseInt(year),cvcCode);

                if(card.validateCard()){
                    stripe.createToken(card, new TokenCallback() {
                        @Override
                        public void onSuccess(@NonNull Token result) {
                            Toast.makeText(getContext(), "hey", Toast.LENGTH_SHORT).show();
                            int orderId = Paper.book().read("orderId");
                            stripeService.postCharges(result.getId(),orderId,"Payment for an order",900,"USD").enqueue(new Callback<Card>() {
                                @Override
                                public void onResponse(Call<Card> call, Response<Card> response) {
                                    try {
                                        Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Card> call, Throwable t) {
                                    Toast.makeText(getContext(), t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return  view;
    }

    private void getCustomerShippingInfo(String accessToken){
        customerService.getCustomer(accessToken).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if(response.isSuccessful()){
                    Customer customer = response.body();
                   paymentAddressOneTextView.setText(customer.getAddress_1());
                   paymentCityTextView.setText(customer.getCity());
                   paymentRegionTextView.setText(customer.getRegion());
                   paymentCountryTextView.setText(customer.getCountry());
                   paymentPostalCogeTextView.setText(customer.getPostal_code());
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }

}
