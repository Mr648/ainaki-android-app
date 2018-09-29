package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.request.VerificationRequest;
import apps.sffa.com.ainaki.model.response.LoginResponse;
import apps.sffa.com.ainaki.util.AinakiPrefrenceManager;
import apps.sffa.com.ainaki.util.AndroidUtilities;
import apps.sffa.com.ainaki.util.FontManager;
import apps.sffa.com.ainaki.util.KEYS;
import apps.sffa.com.ainaki.util.SmsListener;
import apps.sffa.com.ainaki.util.SmsReceiver;
import apps.sffa.com.ainaki.util.ValidationRegex;
import apps.sffa.com.ainaki.webservice.API;
import apps.sffa.com.ainaki.webservice.LoginWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Diako on 13/06/2018.
 */

public class SmsVerificationActivity extends AppCompatActivity {


    private String TAG = "SmsVerificationActivity";

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private final static int TIME_TO_TRY_AGAIN_SECONDS = 60;

    private String phone;


    private Button btnAction;
    private TextView txtCounter;
    private TextView txtView;
    private TextView txtViewSecond;
    private TextInputLayout inputLayoutVerificationCode;
    private TextInputEditText txtVerificationCode;

    private Handler handler;
    private Typeface fontIranSans;


    private CountDownTimer timer;


    View.OnClickListener verifyClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            submitForm(phone);
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_sms);


        Bundle extras = getIntent().getExtras();

        if (extras != null && !extras.isEmpty()) {
            phone = extras.getString("phone");
        } else {
            finish();
        }

        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);

        handler = new Handler();

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAction = (Button) findViewById(R.id.btnAction);
        txtCounter = (TextView) findViewById(R.id.txtCounter);
        txtView = (TextView) findViewById(R.id.txtView);
        txtViewSecond = (TextView) findViewById(R.id.txtViewSecond);
        inputLayoutVerificationCode = (TextInputLayout) findViewById(R.id.inputLayoutVerificationCode);
        txtVerificationCode = (TextInputEditText) findViewById(R.id.txtVerificationCode);


        collapsingToolbar.setTitle("کد فعالسازی");

        txtCounter.setText(Integer.toString(TIME_TO_TRY_AGAIN_SECONDS));

        btnAction.setOnClickListener(verifyClickListener);

        timer = new CountDownTimer(1000 * TIME_TO_TRY_AGAIN_SECONDS, 1000) {
            @Override
            public void onTick(final long l) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtCounter.setText(Long.toString((l / 1000) - 1));
                    }
                });
            }

            @Override
            public void onFinish() {
                btnAction.setEnabled(true);
                btnAction.setText(getResources().getString(R.string.label_try_again_btn));
            }
        }.start();

        setFont();

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void onMessageReceived(String messageText) {
                txtVerificationCode.setText(messageText);
                // TODO verify the code, go to other activity
                // TODO check if user completed profile info!
                skipThisActivity();
            }
        });

    }

    private void setFont() {
        FontManager.setFont(collapsingToolbar, fontIranSans);
        FontManager.setFont(btnAction, fontIranSans);
        FontManager.setFont(txtCounter, fontIranSans);
        FontManager.setFont(inputLayoutVerificationCode, fontIranSans);
        FontManager.setFont(txtVerificationCode, fontIranSans);
        FontManager.setFont(txtView, fontIranSans);
        FontManager.setFont(txtViewSecond, fontIranSans);
    }


    private void sendVerificationCode(final String phone, final String verificationCode) {


        Retrofit retrofit = API.getRetrofit();

        LoginWebService webService = retrofit.create(LoginWebService.class);


        Call<LoginResponse> callWebservice =
                webService.verify(new VerificationRequest(phone, verificationCode));

        callWebservice.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null) {

                    if (!response.body().hasError()) {

                        // TODO Goto Other Part Of Program.
                        AinakiPrefrenceManager.putString(
                                getApplicationContext(),
                                AndroidUtilities.base64Reverse(KEYS.AUTH_KEY),
                                response.body().getAuthKey()
                        );
                        AinakiPrefrenceManager.putString(
                                getApplicationContext(),
                                AndroidUtilities.base64Reverse(KEYS.CSRF_KEY),
                                response.body().getCsrfToken()
                        );
                        Log.i(TAG, "onResponse.SUCCESS: " + response.body().getMessage());
                        Intent intent = new Intent(SmsVerificationActivity.this, SecendStepLoginAcivity.class);
                        startActivity(intent);

                        finish();
                    } else {
                        timer.cancel();
                        Log.i(TAG, "onResponse.FAILURE: " + response.body().getMessage());
                    }

                } else {
                    timer.cancel();
                    Log.i(TAG, "onResponse.NULL: " + null);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
                timer.cancel();
            }
        });
    }

    public void skipThisActivity() {
        // TODO Goto Other Part Of Program.
        AinakiPrefrenceManager.putString(
                getApplicationContext(),
                AndroidUtilities.base64Reverse(KEYS.AUTH_KEY),
                "samplekey"
        );
        AinakiPrefrenceManager.putString(
                getApplicationContext(),
                AndroidUtilities.base64Reverse(KEYS.CSRF_KEY),
                "sampleToken"
        );
        Intent intent = new Intent(SmsVerificationActivity.this, SecendStepLoginAcivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateVerificationCode() {
        if (txtVerificationCode.getText().toString().trim().isEmpty()) {
            inputLayoutVerificationCode.setError(getString(R.string.error_user_verification_code_empty));
            requestFocus(txtVerificationCode);
            return false;

        } else if (!Pattern.matches(ValidationRegex.REGEX_UserPHONE, txtVerificationCode.getText().toString())) {
            inputLayoutVerificationCode.setError(getString((R.string.error_user_verification_code_invalid)));
            requestFocus(txtVerificationCode);
            return false;

        } else {
            inputLayoutVerificationCode.setErrorEnabled(false);

        }
        return true;
    }

    private void submitForm(final String phone) {
        btnAction.setEnabled(false);
        if (!validateVerificationCode()) {
            btnAction.setEnabled(true);
            return;
        }

        timer.start();
//        sendVerificationCode(phone, txtVerificationCode.getText().toString());
        skipThisActivity();
    }

    private void requestFocus(View view) {

        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SmsVerificationActivity.this, LoginActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
        finish();
    }
}
