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
    private TextView txtLabel;

    private Button btnLogin;
    private Typeface fontIranSans;

    private String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);

        txtPhone = (TextInputEditText) findViewById(R.id.txtPhone);
        inputLayoutPhone = (TextInputLayout) findViewById(R.id.inputLayoutPhone);
        txtActivtyTitle = (TextView) findViewById(R.id.txtActivtyTitle);
        txtLabel = (TextView) findViewById(R.id.txtLabel);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        txtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    inputLayoutPhone.setHint("");
                else
                    inputLayoutPhone.setHint("شماره موبایل خود را وارد کنید");
            }
        });



        Bundle extras = getIntent().getExtras();

        if (extras != null && !extras.isEmpty()) {
            String phone = extras.getString("phone");
            txtPhone.setText(phone);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        setFont();
    }

    private void setFont() {
        FontManager.setFont(txtPhone, fontIranSans);
        FontManager.setFont(inputLayoutPhone, fontIranSans);
        FontManager.setFont(txtActivtyTitle, fontIranSans);
        FontManager.setFont(btnLogin, fontIranSans);
        FontManager.setFont(txtLabel, fontIranSans);
    }


    private void submitForm() {

        if (!validateUserPhone()) {
            return;
        }

        skipThisActivity(txtPhone.getText().toString());
    }


    public void skipThisActivity(String phone) {
        Intent intent = new Intent(LoginActivity.this, SmsVerificationActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
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

    // TODO send verification code to user
}
