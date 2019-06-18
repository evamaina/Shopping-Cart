package com.example.shoppingcart;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcart.service.ApiUtilities;
import com.example.shoppingcart.service.CustomerService;
import com.example.shoppingcart.models.CustomerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    Switch switchButton;
    private TextInputEditText firstName, lastName, email;
    private TextInputEditText password, resetPassword;
    private TextInputEditText day, month, year;
    private ImageView genderLine;
    private TextView male, female;
    Button signupButton;
    CustomerService customerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        switchButton = findViewById(R.id.switch_button);

        firstName = findViewById(R.id.text_input_first_name);
        lastName = findViewById(R.id.text_input_last_name);
        password = findViewById(R.id.text_input_password);
        resetPassword = findViewById(R.id.text_input_retype_password);
        email = findViewById(R.id.text_input_email);
        signupButton = findViewById(R.id.signUpButton);
        day = findViewById(R.id.text_input_day);
        month = findViewById(R.id.text_input_month);
        year = findViewById(R.id.text_input_year);
        genderLine = findViewById(R.id.gender_indicator_line);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        customerService = ApiUtilities.customerService();
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                }

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String customerName  = firstName.getText().toString() + " " + lastName.getText().toString();
                    String customerEmail = email.getText().toString();
                    String customerPassword = password.getText().toString();
                    Toast.makeText(SignUpActivity.this, "hjghug", Toast.LENGTH_SHORT).show();
                    registerCustomer(customerEmail, customerName, customerPassword);
            }
        });

    }

    public void signIn(View view) {
        startActivity(new Intent(this, SignInActivity.class));
    }

    private boolean validateFirstName() {
        String firstNameInput = firstName.getText().toString().trim();
        if (firstNameInput.isEmpty()) {
            firstName.setError("Field can't be empty");
            return false;
        }
            return true;
    }

    private boolean validateLasttName() {
        String lastNameInput = lastName.getText().toString().trim();
        if (lastNameInput.isEmpty()) {
            lastName.setError("Field can't be empty");
            return false;
        }
            return true;
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString();
        String resetPasswordInput = resetPassword.getText().toString();
        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        }

        if (!passwordInput.equals(resetPasswordInput)){
            resetPassword.setError("Password Mismatch!");
            return false;
        }
            return true;

    }

    private boolean validateTime() {
        String dayInput = day.getText().toString();
        String monthInput = month.getText().toString();
        String yearInput = year.getText().toString();
        if (dayInput.isEmpty()) {
            day.setError("Field can't be empty");
            return false;
        }
        if (monthInput.isEmpty()) {
            month.setError("Field can't be empty");
            return false;
        }
        if (yearInput.isEmpty()) {
            year.setError("Field can't be empty");
            return false;
        }
        return true;
    }

    public void setMale(View view) {
        genderLine.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_male_indicator));
        female.setAlpha(1f);
        male.setAlpha(0.6f);

    }

    public void setFemale(View view) {
        genderLine.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_female_indicator));
        female.setAlpha(0.6f);
        male.setAlpha(1f);
    }






    public boolean confirmInput() {
        return !validateFirstName() && !validateLasttName() && !validatePassword() && !validateTime();


    }

    private void registerCustomer(String customerEmail, String customerName, String customerPassword) {
        customerService.registerCustomer(customerName, customerEmail, customerPassword).enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(SignUpActivity.this, response.body().getAccessToken(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignUpActivity.this, "error occured", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
