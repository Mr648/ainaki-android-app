package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
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
import apps.sffa.com.ainaki.ui.dialog.PickerDialog;
import apps.sffa.com.ainaki.util.FontManager;

/**
 * Created by Diako on 30/05/2018.
 */

public class SecendStepLoginAcivity extends AppCompatActivity {


    private Button btnSkip;
    private Button btnSubmit;
    private TextView textView;

    private TextInputEditText txtName;
    private TextInputEditText txtFamily;

    private TextInputLayout inputLayoutName;
    private TextInputLayout inputLayoutFamily;
    private EditText txtBirthDate;

    private Typeface fontIranSans;


    private void setFont() {
        FontManager.setFont(btnSkip, fontIranSans);
        FontManager.setFont(btnSubmit, fontIranSans);

        FontManager.setFont(txtName, fontIranSans);
        FontManager.setFont(txtFamily, fontIranSans);

        FontManager.setFont(inputLayoutName, fontIranSans);
        FontManager.setFont(inputLayoutFamily, fontIranSans);

        FontManager.setFont(txtBirthDate, fontIranSans);
        FontManager.setFont(textView, fontIranSans);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_step2);
        fontIranSans = FontManager.getTypeface(getApplicationContext(), FontManager.IRANSANS_TEXTS);

        txtBirthDate = (EditText) findViewById(R.id.txtBirthDate);

        textView = (TextView) findViewById(R.id.textView);

        btnSkip = (Button) findViewById(R.id.btnSkip);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        inputLayoutName= (TextInputLayout) findViewById(R.id.inputLayoutName);
        inputLayoutFamily= (TextInputLayout) findViewById(R.id.inputLayoutFamily);

        txtName = (TextInputEditText) findViewById(R.id.txtName);
        txtFamily = (TextInputEditText) findViewById(R.id.txtFamily);

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
                final PickerDialog dialog = new PickerDialog(SecendStepLoginAcivity.this);
//                final DatePickerDialog dialog = new DatePickerDialog(SecendStepLoginAcivity.this);
//                dialog.setDateListener(new DatePickerDialog.DatePickedListener() {
//                    @Override
//                    public void dateSelected(DatePickerDialog.Date selectedDate) {
//                        txtBirthDate.setText(selectedDate.toString());
//                        dialog.dismiss();
//                    }
//                });
                dialog.show();

            }
        });

        setFont();
    }
}
