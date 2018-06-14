package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.request.LoginRequest;
import apps.sffa.com.ainaki.model.response.LoginResponse;
import apps.sffa.com.ainaki.util.FontManager;
import apps.sffa.com.ainaki.util.ValidationRegex;
import apps.sffa.com.ainaki.webservice.API;
import apps.sffa.com.ainaki.webservice.LoginWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diako on 24/05/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText txtPhone;
    private TextInputLayout inputLayoutPhone;
    private TextView txtActivtyTitle;


    private Button btnLogin;

    private Typeface fontIranSans;


    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);
        CoordinatorLayout coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        txtPhone = (TextInputEditText) findViewById(R.id.txtPhone);


        inputLayoutPhone = (TextInputLayout) findViewById(R.id.inputLayoutPhone);


        txtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    inputLayoutPhone.setHint("");
                else
                    inputLayoutPhone.setHint("09xxxxxxxxx");
            }
        });
        txtActivtyTitle = (TextView) findViewById(R.id.txtActivtyTitle);
        //  txtDontHaveAccount = (TextView) findViewById(R.id.txtDontHaveAccount);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        Bundle extras = getIntent().getExtras();

        if (extras != null && !extras.isEmpty()) {
            String phone = extras.getString("phone");
            txtPhone.setText(phone);
        }

        setFont();
//        inputLayoutEmail.setErrorEnabled(true);
        //  inputLayoutPassword.setErrorEnabled(true);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

    }

    private void setFont() {
        FontManager.setFont(txtPhone, fontIranSans);
        // FontManager.setFont(txtPassword, fontIranSans);
        FontManager.setFont(inputLayoutPhone, fontIranSans);
        //  FontManager.setFont(inputLayoutPassword, fontIranSans);

        FontManager.setFont(txtActivtyTitle, fontIranSans);
        //FontManager.setFont(txtDontHaveAccount, fontIranSans);

        // FontManager.setFont(btnRegister, fontIranSans);
        FontManager.setFont(btnLogin, fontIranSans);
        // FontManager.setFont(btnForgetPassword, fontIranSans);

    }


    private void submitForm() {

        if (!validateUserPhone()) {
            return;
        }


        sendSms(txtPhone.getText().toString());
    }

    private void sendSms(final String phone) {

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        LoginWebService webService = retrofit.create(LoginWebService.class);


        Call<LoginResponse> callWebservice =
                webService.login(new LoginRequest(phone));

        callWebservice.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {

                    if (!response.body().hasError()) {

                        // Login Successfull
                        // TODO go to verification activity
                        Log.i(TAG, "onResponse.SUCCESS: " + response.body().getMessage());

                        Intent intent = new Intent(LoginActivity.this, SmsVerificationActivity.class);
                        intent.putExtra("phone", phone);

                        startActivity(intent);
                        finish();

                    } else {
                        Log.i(TAG, "onResponse.FAILURE: " + response.body().getMessage());
                    }

                } else {
                    Log.i(TAG, "onResponse.NULL: " + null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    private boolean validateUserPhone() {
        if (txtPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError(getString(R.string.error_user_phone_empty));
            requestFocus(txtPhone);
            return false;

        } else if (!Pattern.matches(ValidationRegex.REGEX_UserPHONE, txtPhone.getText().toString())) {
            inputLayoutPhone.setError(getString((R.string.error_user_phone_invalid)));
            requestFocus(txtPhone);
            return false;

        } else {
            inputLayoutPhone.setErrorEnabled(false);

        }
        return true;
    }

    private void requestFocus(View view) {

        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

    }

}
