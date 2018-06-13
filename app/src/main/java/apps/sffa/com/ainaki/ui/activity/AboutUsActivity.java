package apps.sffa.com.ainaki.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.util.FontManager;

/**
 * Created by Diako on 15/05/2018.
 */

public class AboutUsActivity extends AppCompatActivity {

    Typeface fontMaterialIcon;
    Typeface fontSocialIcon;
    Typeface fontIranSans;

    TextView txtWeb;
    TextView txtShare;
    TextView txtSms;
    TextView txtRate;
    TextView txtLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        fontMaterialIcon = FontManager.getTypeface(getApplicationContext(), FontManager.MATERIAL_ICONS);
        fontSocialIcon = FontManager.getTypeface(getApplicationContext(), FontManager.SOCIAL_ICONS);

        txtWeb = (TextView) findViewById(R.id.txtWeb);
        txtShare = (TextView) findViewById(R.id.txtShare);
        txtSms = (TextView) findViewById(R.id.txtEmail);
        txtRate = (TextView) findViewById(R.id.txtRate);
        txtLocation = (TextView) findViewById(R.id.txtLocation);

        FontManager.setFont(txtWeb,fontMaterialIcon);
        FontManager.setFont(txtShare,fontMaterialIcon);
        FontManager.setFont(txtSms,fontMaterialIcon);
        FontManager.setFont(txtRate,fontMaterialIcon);
        FontManager.setFont(txtLocation,fontMaterialIcon);

    }


}
