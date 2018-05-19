package apps.sffa.com.ainaki;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import layout.RegistrationFirstStepFragment;
import layout.RegistrationSecondStepFragment;
import layout.SendSupportTicketFragment;

public class MainActivity extends AppCompatActivity implements RegistrationFirstStepFragment.OnFragmentInteractionListener,
        RegistrationSecondStepFragment.OnFragmentInteractionListener , SendSupportTicketFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTransaction ft =  getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.add(R.id.content, new RegistrationFirstStepFragment()).commit();
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
        FragmentTransaction ft =  getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.content, new RegistrationSecondStepFragment()).commit();
    }

    @Override
    public void onRegistrationSecondStepInteraction() {
        FragmentTransaction ft =  getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_out_right, R.anim.slide_in_left);
        ft.replace(R.id.content, new SendSupportTicketFragment()).commit();
    }

    @Override
    public void onSendSupportTicketInteraction() {

    }
}
