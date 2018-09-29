package apps.sffa.com.ainaki.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.ProductAdapter;
import apps.sffa.com.ainaki.model.Model;
import apps.sffa.com.ainaki.model.request.GeneralRequest;
import apps.sffa.com.ainaki.util.AndroidUtilities;
import apps.sffa.com.ainaki.util.KEYS;
import apps.sffa.com.ainaki.webservice.API;
import apps.sffa.com.ainaki.webservice.ProductListWebService;
import apps.sffa.com.ainaki.webservice.UserWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diako on 21/05/2018.
 */

public class ProductsListActivity extends AppCompatActivity {

    RecyclerView recItems;
    private final String TAG = "ProductsListActivity";
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle extras = getIntent().getExtras();

        if (extras != null && !extras.isEmpty()) {

            final String category = extras.getString("CATEGORY");
            final String filter = extras.getString("FILTER");
            final String title = extras.getString("TITLE");

            recItems = (RecyclerView) findViewById(R.id.recItems);
            collapsingToolbar.setTitle(title);

            recItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            recItems.setNestedScrollingEnabled(false);
            recItems.setAdapter(new ProductAdapter(getApplicationContext(), initProducts(null)));
//            fetchProductsData(recItems, category, filter);
//            recItems.setAdapter(new ProductAdapter(ProductsListActivity.this,initProducts(null)));
        } else {
            finish();
            Toast.makeText(ProductsListActivity.this, "Error in init extras", Toast.LENGTH_SHORT).show();
        }


    }


    private ArrayList<Model> initProducts(String type) {

        ArrayList<Model> products = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            products.add(new Model(i));

        }
        return products;
    }

    private void fetchProductsData(final RecyclerView recItems, String category, String filter) {

        Call<List<Model>> callProducts = null;
        switch (category) {
            case "FAVORITES": {
                UserWebService api = API.getRetrofit().create(UserWebService.class);
                callProducts = api.getFavoriteProducts(new GeneralRequest(AndroidUtilities.base64Reverse(KEYS.AUTH_KEY)));
            }
            break;
            default: {
                ProductListWebService api = API.getRetrofit().create(ProductListWebService.class);
                callProducts = api.getProducts(category, filter);
            }

        }


        callProducts.enqueue(new Callback<List<Model>>() {

            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.body() != null) {
                    if (!response.body().isEmpty()) {
                        ArrayList<Model> products = new ArrayList<>();
                        products.addAll(response.body());
                        recItems.setAdapter(new ProductAdapter(getApplicationContext(), products));
                        Log.i(TAG, "onResponse.SUCCESS: " + response.body());
                    } else {
                        Log.i(TAG, "onResponse.FAILURE: " + response);
                    }
                } else {
                    Log.i(TAG, "onResponse.NULL: " + null);
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(ProductsListActivity.this, "This is Error onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
