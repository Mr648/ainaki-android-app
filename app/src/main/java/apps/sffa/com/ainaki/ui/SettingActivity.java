package apps.sffa.com.ainaki.ui;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import apps.sffa.com.ainaki.R;

/**
 * Created by mr-code on 5/15/2018.
 */

public class SettingActivity extends PreferenceActivity {
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.app_setting);
    }
}
