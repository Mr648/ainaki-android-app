package apps.sffa.com.ainaki.ui;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import apps.sffa.com.ainaki.R;
import layout.RegistrationFirstStepFragment;
import layout.RegistrationSecondStepFragment;
import layout.SharePhotoFragment;

public class MainActivity extends AppCompatActivity implements RegistrationFirstStepFragment.OnFragmentInteractionListener,
        RegistrationSecondStepFragment.OnFragmentInteractionListener,SharePhotoFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_setting) {

        } else if (id == R.id.action_aboutus) {
        } else if (id == R.id.action_list_glass) {
        } else if (id == R.id.action_faq) {
        } else if (id == R.id.action_list_accessories) {
        } else if (id == R.id.action_list_lens) {
        } else if (id == R.id.action_list_purchases) {
        } else if (id == R.id.action_list_favorite) {
        } else if (id == R.id.action_ticket) {
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);

        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            navigationView.setLayoutDirection(NavigationView.LAYOUT_DIRECTION_RTL);
        }
        navigationView.setNavigationItemSelectedListener(this);


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.add(R.id.content, new SharePhotoFragment()).commit();
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    static {
        System.loadLibrary("native-lib");
    }


    @Override
    public void onRegistrationFirstStepInteraction() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.content, new RegistrationSecondStepFragment()).commit();
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
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }
}
