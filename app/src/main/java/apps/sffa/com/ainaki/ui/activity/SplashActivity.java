package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
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

        String authKey = AinakiPrefrenceManager.getString(getApplicationContext(), AndroidUtilities.base64Reverse(AppKeys.AUTH_KEY), null);
        final boolean isAuthenticated = (authKey != null && !authKey.isEmpty());

//        findViewById(R.id.imgLogo).animate().translationX(150).translationXBy(0).rotation(360).setDuration(1500).alpha(0.0f).setDuration(1000).alpha(1.0f).setDuration(1000).start();
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        findViewById(R.id.imgLogo).startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (isAuthenticated) {
                    startActivity(new Intent(SplashActivity.this, GenderActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
