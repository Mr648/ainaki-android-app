package apps.sffa.com.ainaki.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Diako on 24/05/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText txtPhone;
    private TextInputEditText txtPassword;
    private TextInputLayout inputLayoutPhone;
    private TextInputLayout inputLayoutPassword;
    private TextView txtActivtyTitle;
    private TextView txtDontHaveAccount;


    private Button btnLogin;
    private Button btnRegister;
    private Button btnForgetPassword;

    private CoordinatorLayout coordinator;

    private Typeface fontIranSans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);

        txtPhone = (TextInputEditText) findViewById(R.id.txtPhone);
        //txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.inputLayoutPhone);
     //   inputLayoutPassword = (TextInputLayout) findViewById(R.id.inputLayoutPassword);

       // btnForgetPassword = (Button) findViewById(R.id.btnForgetPassword);
        //btnRegister = (Button) findViewById(R.id.btnRegister);

        txtActivtyTitle = (TextView) findViewById(R.id.txtActivtyTitle);
      //  txtDontHaveAccount = (TextView) findViewById(R.id.txtDontHaveAccount);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        setFont();
//        inputLayoutEmail.setErrorEnabled(true);
//        inputLayoutPassword.setErrorEnabled(true);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                submitForm();
//            }
//        });

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

//    private void submitForm() {
//
//        if (!validateUserEmail()) {
//            return;
//        }
//
//        if (!validateUserPassword()) {
//            return;
//        }
//        login(txtEmail.getText().toString(), txtPassword.getText().toString());
//    }
//
//    private void login(String email, String password) {
//        Gson gson = new GsonBuilder().setLenient().create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        LoginWebService webService = retrofit.create(LoginWebService.class);
//
//
//        Call<LoginResponse> callWebservice =
//                webService.login(new LoginRequest(email, password));
//
//        callWebservice.enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//                if (response.body() != null) {
//
//                    if (!response.body().hasError()) {
//                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//
//                } else {
//                    Toast.makeText(LoginActivity.this, "خطا در اتصال به اینترنت", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "خطا در اتصال به اینترنت", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

//    private boolean validateUserEmail() {
//        if (txtEmail.getText().toString().trim().isEmpty()) {
//            inputLayoutEmail.setError(getString(R.string.error_user_email_empty));
//            requestFocus(txtEmail);
//            return false;
//
//        } else if (!Pattern.matches(ValidationRegex.REGEX_UserEMAIL, txtEmail.getText().toString())) {
//            inputLayoutEmail.setError(getString((R.string.error_user_email_invalid)));
//            requestFocus(txtEmail);
//            return false;
//
//        } else {
//            inputLayoutEmail.setErrorEnabled(false);
//
//        }
//        return true;
//    }
//
//    private boolean validateUserPassword() {
//        if (txtPassword.getText().toString().trim().isEmpty()) {
//            inputLayoutPassword.setError(getString(R.string.error_user_password_empty));
//            requestFocus(txtPassword);
//            return false;
//
//        } else if (!Pattern.matches(ValidationRegex.REGEX_UserPASS, txtPassword.getText().toString())) {
//            inputLayoutPassword.setError(getString((R.string.error_user_password_invalid)));
//            requestFocus(txtPassword);
//            return false;
//
//        } else {
//            inputLayoutPassword.setErrorEnabled(false);
//
//        }
//        return true;
//    }
//
//    private void requestFocus(View view) {
//
//        if (view.requestFocus()) {
//            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        }
//
//    }

}
