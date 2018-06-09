package apps.sffa.com.ainaki.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.ImageSliderAdapter;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Diako on 03/06/2018.
 */

public class ShowProductActivity extends AppCompatActivity{


    private ViewPager mPager;
    private CircleIndicator indicator;
    private static int currentPage = 0;
    private static final List<Integer> images = Arrays.asList(
            R.drawable.ic_bug_report_black_18dp,
            R.drawable.ic_favorite_black_18dp,
            R.drawable.ic_help_black_18dp,
            R.drawable.ic_instagram
    );

    private TabHost TabHostWindow;


    private ArrayList<Integer> imageList = new ArrayList<Integer>();

    private void initializeImageSlider() {

        imageList.addAll(images);
        mPager.setAdapter(new ImageSliderAdapter(getApplicationContext(), imageList));
        indicator.setViewPager(mPager);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == images.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        mPager = (ViewPager) findViewById(R.id.pager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        initializeImageSlider();

        TabHostWindow = (TabHost)findViewById(R.id.tabHostWindow);

        TabHostWindow.setup();

        //Tab 1
        TabHost.TabSpec spec = TabHostWindow.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("نظرات کاربران");
        TabHostWindow.addTab(spec);

        //Tab 2
        spec = TabHostWindow.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("مشخصات محصول");
        TabHostWindow.addTab(spec);

        //Tab 3
        spec = TabHostWindow.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("توضیحات");
        TabHostWindow.addTab(spec);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
