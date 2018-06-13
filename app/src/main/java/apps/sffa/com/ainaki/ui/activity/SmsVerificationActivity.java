package apps.sffa.com.ainaki.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import apps.sffa.com.ainaki.R;

/**
 * Created by Diako on 13/06/2018.
 */

public class SmsVerificationActivity extends AppCompatActivity {

    private TextView txtTimer;
    private TextView txtRefreshSms;
    private TextInputEditText txtVerification;
    private CountDownTimer timer;
    private Button btnConfirm;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_sms);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("کد فعالسازی");

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        txtTimer = (TextView) findViewById(R.id.txtTimer);
        txtRefreshSms = (TextView) findViewById(R.id.txtRefreshSms);
        txtVerification = (TextInputEditText) findViewById(R.id.txtVerification);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verify(txtVerification.getText().toString());
            }
        });
    }

    boolean verify(String key) {
        //TODO verify

        return false;
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

}
