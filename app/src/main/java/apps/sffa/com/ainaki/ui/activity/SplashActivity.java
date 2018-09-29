package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import apps.sffa.com.ainaki.R;

/**
 * Created by Diako on 29/05/2018.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final boolean isAuthenticated = checkLogin();


//        findViewById(R.id.imgLogo).animate().translationX(150).translationXBy(0).rotation(360).setDuration(1500).alpha(0.0f).setDuration(1000).alpha(1.0f).setDuration(1000).start();
//        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        Animation animation = new AlphaAnimation(0.5f,1.0f);
        animation.setDuration(2000);
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

    private boolean checkLogin() {


//        String authKey = AinakiPrefrenceManager.getString(getApplicationContext(), AndroidUtilities.base64Reverse(KEYS.AUTH_KEY), null);

        // TODO check if user is logged in here

        return false;
    }
}
