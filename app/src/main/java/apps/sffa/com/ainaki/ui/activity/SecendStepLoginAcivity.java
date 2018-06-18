package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.ui.dialog.DatePickerDialog;

/**
 * Created by Diako on 30/05/2018.
 */

public class SecendStepLoginAcivity extends AppCompatActivity {


    private Button btnSkip;
    private Button btnSubmit;

    private TextInputEditText inputLayoutName;
    private TextInputEditText inputLayoutFamily;
    private TextView txtName;
    private TextView txtFamily;
    private EditText txtBirthDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_step2);

        btnSkip = (Button) findViewById(R.id.btnSkip);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtBirthDate = (EditText) findViewById(R.id.txtBirthDate);

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
        txtBirthDate.setFocusable(false);
        txtBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatePickerDialog dialog = new DatePickerDialog(SecendStepLoginAcivity.this);
                dialog.setDateListener(new DatePickerDialog.DatePickedListener() {
                    @Override
                    public void dateSelected(DatePickerDialog.Date selectedDate) {
                        txtBirthDate.setText(selectedDate.toString());
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });
    }
}
