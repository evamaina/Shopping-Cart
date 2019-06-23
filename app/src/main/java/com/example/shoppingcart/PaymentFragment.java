package com.example.shoppingcart;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import dmax.dialog.SpotsDialog;
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
    SpotsDialog.Builder builder;
    AlertDialog dialog;

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
        builder = new SpotsDialog.Builder().setContext(getContext());
        builder.setMessage("Loading");
        dialog = builder.build();
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

                if(cardNumber.isEmpty()){
                    Toast.makeText(getContext(), "Please input card number ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(month.isEmpty()){
                    Toast.makeText(getContext(), "Please input expiry month", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(year.isEmpty()){
                    Toast.makeText(getContext(), "Please input expiry year ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cvcCode.isEmpty()){
                    Toast.makeText(getContext(), "Please input cvc code", Toast.LENGTH_SHORT).show();
                    return;
                }

                card = Card.create(cardNumber,Integer.parseInt(month), Integer.parseInt(year),cvcCode);

                if(card.validateCard()){
                    showDialogBox();
                    stripe.createToken(card, new TokenCallback() {
                        @Override
                        public void onSuccess(@NonNull Token result) {
                            dismissDialogBox();
                            int orderId = Paper.book().read("orderId");
                            showDialogBox();
                            stripeService.postCharges(result.getId(),orderId,"Payment for an order",
                                    900,"USD").enqueue(new Callback<Card>() {
                                @Override
                                public void onResponse(Call<Card> call, Response<Card> response) {
                                    dismissDialogBox();
                                    Toast.makeText(getContext(), "Payment Complete ", Toast.LENGTH_SHORT).show();
                                    onRequestSuccessListener.onRequestSuccessListener(2);
                                }
                                @Override
                                public void onFailure(Call<Card> call, Throwable t) {
                                    dismissDialogBox();
                                    Toast.makeText(getContext(), t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onError(@NonNull Exception e) {
                            dismissDialogBox();
                            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    dismissDialogBox();
                    Toast.makeText(getContext(), "In valid card details", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialogBox(){
        if(!dialog.isShowing()){
            dialog.show();
        }
    }

    private void dismissDialogBox(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }

}
