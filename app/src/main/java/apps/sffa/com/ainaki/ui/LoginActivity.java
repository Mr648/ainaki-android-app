package apps.sffa.com.ainaki.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.util.FontManager;

/**
 * Created by Diako on 24/05/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText txtEmail;
    private TextInputEditText txtPassword;
    private TextInputLayout inputLayoutEmail;
    private TextInputLayout inputLayoutPassword;
    private TextView txtForgetPassword;
    private TextView txtRegister;
    private TextView txtActivtyTitle;
    private TextView txtDontHaveAccount;
    private Button btnLogin;
    private CoordinatorLayout coordinator;

    private Typeface fontIranSans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);

        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.inputLayoutEmail);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.inputLayoutPassword);

        txtForgetPassword = (TextView) findViewById(R.id.txtForgetPassword);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        txtActivtyTitle = (TextView) findViewById(R.id.txtActivtyTitle);
        txtDontHaveAccount = (TextView) findViewById(R.id.txtDontHaveAccount);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        FontManager.setFont(txtEmail, fontIranSans);
        FontManager.setFont(txtPassword, fontIranSans);
        FontManager.setFont(inputLayoutEmail, fontIranSans);
        FontManager.setFont(inputLayoutPassword, fontIranSans);

        FontManager.setFont(txtForgetPassword, fontIranSans);
        FontManager.setFont(txtRegister, fontIranSans);
        FontManager.setFont(txtActivtyTitle, fontIranSans);
        FontManager.setFont(txtDontHaveAccount, fontIranSans);

        FontManager.setFont(btnLogin, fontIranSans);
    }
}
