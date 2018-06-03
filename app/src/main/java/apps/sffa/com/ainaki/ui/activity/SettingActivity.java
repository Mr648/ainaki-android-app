package apps.sffa.com.ainaki.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
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
        View view = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.xml.app_setting,null);
        addContentView(view,null);
    }
}
