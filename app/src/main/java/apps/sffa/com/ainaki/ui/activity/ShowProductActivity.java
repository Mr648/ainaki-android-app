package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.ImageSliderAdapter;
import apps.sffa.com.ainaki.adapter.ProductAdapter;
import apps.sffa.com.ainaki.model.Product;
import apps.sffa.com.ainaki.model.request.FavoriteRequest;
import apps.sffa.com.ainaki.model.response.GeneralResponse;
import apps.sffa.com.ainaki.util.AinakiPrefrenceManager;
import apps.sffa.com.ainaki.util.AndroidUtilities;
import apps.sffa.com.ainaki.util.AppKeys;
import apps.sffa.com.ainaki.webservice.API;
import apps.sffa.com.ainaki.webservice.UserWebService;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diako on 03/06/2018.
 */

public class ShowProductActivity extends AppCompatActivity {

    private String TAG = "ShowProductActivity";

    private ViewPager mPager;
    private boolean like = false;
    private CircleIndicator indicator;
    private static int currentPage = 0;
    private static final List<Integer> images = Arrays.asList(
            R.drawable.ic_bug_report_black_18dp,
            R.drawable.ic_favorite_black_18dp,
            R.drawable.ic_help_black_18dp,
            R.drawable.ic_instagram
    );

    private TabHost TabHostWindow;
    private ImageView imgFav;
    private ImageView imgShareProduct;
    private ImageView imgCamera;


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

    private Integer productId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);

        Bundle extras = getIntent().getExtras();
        if (extras != null && !extras.isEmpty()) {
            productId = extras.getInt("PRODUCT_ID");
        } else {
            finish();
        }

        mPager = (ViewPager) findViewById(R.id.pager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        initializeImageSlider();

        TabHostWindow = (TabHost) findViewById(R.id.tabHostWindow);
        imgFav = (ImageView) findViewById(R.id.imgFav);
        imgShareProduct = (ImageView) findViewById(R.id.imgShareProduct);
        imgCamera = (ImageView) findViewById(R.id.imgCamera);

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

        for (int i = 0; i < TabHostWindow.getTabWidget().getChildCount(); i++) {

            TextView tv = (TextView) TabHostWindow.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(11);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

            /*TabHostWindow.setOnTabChangedListener(new TabHost.OnTabChangeListener(){



            @Override
            public void onTabChanged(String tabId) {




                int tab = TabHostWindow.getCurrentTab();
                // When tab is selected
                TabHostWindow.getTabWidget().getChildAt(TabHostWindow.getCurrentTab()).setBackgroundColor(getResources().getColor(R.color.colorTab));
            }});*/


        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!like) {
                    likeTheProduct();
                } else {
                    dislikeTheProduct();
                }

                like = !like;

            }
        });

        imgShareProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "فریم و لنزهای متفاوت را در اپلیکیشن عینکی بر روی صورت خود امتحان کنید";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA_SECURE);
                startActivity(mintent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void likeTheProduct() {

        UserWebService api = API.getRetrofit().create(UserWebService.class);

        String authKey = AinakiPrefrenceManager.getString(ShowProductActivity.this, AndroidUtilities.base64Reverse(AppKeys.AUTH_KEY), null);

        Call<GeneralResponse> callProducts = api.like(new FavoriteRequest(authKey, Integer.toString(productId), "eyeglass"));


        callProducts.enqueue(new Callback<GeneralResponse>() {

            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.body() != null) {
                    if (!response.body().hasError()) {
                        imgFav.setImageResource(R.drawable.ic_favorite_black_18dp);
                        imgFav.setColorFilter((getResources().getColor(R.color.colorPrimary)));
                        Log.i(TAG, "onResponse.SUCCESS: " + response.body().getMessage());
                    } else {
                        Log.i(TAG, "onResponse.FAILURE: " + response.body().getMessage());
                    }
                } else {
                    Log.i(TAG, "onResponse.NULL: " + null);
                }

            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void dislikeTheProduct() {

        UserWebService api = API.getRetrofit().create(UserWebService.class);
        String authKey = AinakiPrefrenceManager.getString(ShowProductActivity.this, AndroidUtilities.base64Reverse(AppKeys.AUTH_KEY), null);

        Call<GeneralResponse> callProducts = api.dislike(new FavoriteRequest(authKey, Integer.toString(productId), "eyeglass"));


        callProducts.enqueue(new Callback<GeneralResponse>() {

            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.body() != null) {
                    if (!response.body().hasError()) {
                        imgFav.setImageResource(R.drawable.ic_favorite_border_black_18dp);
                        imgFav.setColorFilter(getResources().getColor(R.color.colorBlack));
                        Log.i(TAG, "onResponse.SUCCESS: " + response.body().getMessage());
                    } else {
                        Log.i(TAG, "onResponse.FAILURE: " + response.body().getMessage());
                    }
                } else {
                    Log.i(TAG, "onResponse.NULL: " + null);
                }

            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
