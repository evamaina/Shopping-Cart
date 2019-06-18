package com.example.shoppingcart;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.CustomerService;
import com.example.shoppingcart.models.CustomerResponse;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    CustomerService customerService;
    private EditText email, password;
    private Button signInButton;
    SpotsDialog.Builder builder;
    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = findViewById(R.id.signInEmail);
        password = findViewById(R.id.signInPassword);
        signInButton = findViewById(R.id.signInButton);
        customerService = ApiUtilities.customerService();
        Paper.init(getApplicationContext());
        builder  = new SpotsDialog.Builder().setContext(this);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerEmail = email.getText().toString();
                String customerPassword = password.getText().toString();
                loginCustomer(customerEmail, customerPassword);

            }
        });
    }

    public void resetPassword(View view) {
        startActivity(new Intent(this, ResetPasswordActivity.class));
    }

    public void signUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }



    private void loginCustomer(String customerEmail, String customerPassword) {
        showProgressDialog();
        customerService.loginCustomer(customerEmail, customerPassword).enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()){
                    dismissProgressDialog();
                    String access_token = response.body().getAccessToken();
                    Paper.book().write("accessToken", access_token);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    return;
                }
                dismissProgressDialog();
                Toast.makeText(SignInActivity.this, response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                dismissProgressDialog();
                Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void dismissProgressDialog() {
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private void showProgressDialog() {
        builder.setMessage("Loading");
        builder.setCancelable(false);
        dialog = builder.build();
        dialog.show();
    }

}
