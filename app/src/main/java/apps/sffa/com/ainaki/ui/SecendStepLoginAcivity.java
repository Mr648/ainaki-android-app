package apps.sffa.com.ainaki.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import apps.sffa.com.ainaki.R;

/**
 * Created by Diako on 30/05/2018.
 */

public class SecendStepLoginAcivity extends AppCompatActivity {


    private Button btnSkip;
    private Button btnSubmit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_step2);

        btnSkip = (Button) findViewById(R.id.btnSkip);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecendStepLoginAcivity.this, GenderActivity.class));
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO ADD TEXT TO DATABASE
                startActivity(new Intent(SecendStepLoginAcivity.this, GenderActivity.class));
                finish();
            }
        });
    }
}
