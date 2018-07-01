package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.util.AinakiPrefrenceManager;
import apps.sffa.com.ainaki.util.AndroidUtilities;
import apps.sffa.com.ainaki.util.AppKeys;

/**
 * Created by Diako on 29/05/2018.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.imgLogo).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
        String authKey = AinakiPrefrenceManager.getString(getApplicationContext(), AndroidUtilities.base64Reverse(AppKeys.AUTH_KEY), null);
        final boolean isAuthenticated = (authKey != null && !authKey.isEmpty());

        new CountDownTimer(5000, 100) {
            @Override
            public void onTick(final long l) {

            }

            @Override
            public void onFinish() {
                if (isAuthenticated) {
                    startActivity(new Intent(SplashActivity.this, GenderActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }.start();

    }
}
