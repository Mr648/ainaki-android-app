package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.Gender;
//import layout.CircularProductViewerFragment;
//import layout.GenderFragment;
//import layout.HomeFragment;
//import layout.RegistrationFirstStepFragment;
//import layout.RegistrationSecondStepFragment;
//import layout.SharePhotoFragment;
import apps.sffa.com.ainaki.ui.fragment.HomeFragment;
import apps.sffa.com.ainaki.ui.fragment.RegistrationFirstStepFragment;
import apps.sffa.com.ainaki.ui.fragment.RegistrationSecondStepFragment;
import apps.sffa.com.ainaki.ui.fragment.SharePhotoFragment;

public class MainActivity extends AppCompatActivity
        implements RegistrationFirstStepFragment.OnFragmentInteractionListener,
        RegistrationSecondStepFragment.OnFragmentInteractionListener,
        SharePhotoFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;


    private static class FragmentTags {
        static final String GENDER_FRAGMENT = "GENDER_FRAGMENT";
        static final String HOME_FRAGMENT = "HOME_FRAGMENT";
        static final String SHARE_PHOTO_FRAGMENT = "SHARE_PHOTO_FRAGMENT";
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_aboutus) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_list_glass) {
            gotoProductsActivity("لیست عینک‌ها", "FAVORITES", "all");
        } else if (id == R.id.action_faq) {
        } else if (id == R.id.action_list_accessories) {
            gotoProductsActivity("لیست لوازم و وسایل", "FAVORITES", "all");
        } else if (id == R.id.action_list_lens) {
            Intent intent = new Intent(MainActivity.this, LensListActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_list_purchases) {
        } else if (id == R.id.action_list_favorite) {
            gotoProductsActivity("لیست علاقه مندی ها", "FAVORITES", "all");
        } else if (id == R.id.action_ticket) {
        } else if (id == R.id.action_profile) {
            Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
            startActivity(intent);
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoProductsActivity(String title, String category, String filter) {
        Intent intent = new Intent(MainActivity.this, ProductsListActivity.class);
        intent.putExtra("TITLE", title);
        intent.putExtra("CATEGORY", category);
        intent.putExtra("FILTER", filter);
        startActivity(intent);
    }

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {
            selectGender((Gender) extras.get("GENDER"));
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
//toggle.setDrawerIndicatorEnabled(false);
        drawer.setDrawerListener(toggle);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            navigationView.setLayoutDirection(NavigationView.LAYOUT_DIRECTION_RTL);
        }
        navigationView.setNavigationItemSelectedListener(this);
        disableNavigationViewScrollbars(navigationView);

//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
//
//        /**
//         *  TODO Don't Add addToBackStack Here.
//         */
//        ft.replace(R.id.content, new GenderFragment()).commit();


//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    static {
//
//        org.opencv.android.OpenCVLoader.initDebug();
//        System.loadLibrary("faceDetection");
        /**
         * NOTE: OpenCVLoader.initDebug() must be used for debugging purposes only as when you are developing locally on your machine.
         * But for production purposes where you need to release the app on Play Store, etc.
         * you must use OpenCVLoader.initAsync().
         * Actually initializing the OpenCVLoader takes some time depending upon the phone.
         * So if you load it uisng initDebug(), then it would be executed in the main thread,
         * which may block the UI for a fractional time.
         * So it is advised to load the OpenCV in background which can be achieved using initAsync()
         */
    }


    @Override
    public void onRegistrationFirstStepInteraction() {

    }

    @Override
    public void onRegistrationSecondStepInteraction() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            //TODO ADD SEARCH HERE
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }


    private void selectGender(Gender gender) {
        // TODO go to home fragment and pass the gender
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.content, HomeFragment.newInstance(gender)).commit();

    }
}
