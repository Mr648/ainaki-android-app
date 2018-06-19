package apps.sffa.com.ainaki.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.ProductAdapter;
import apps.sffa.com.ainaki.model.Favorite;
import apps.sffa.com.ainaki.model.Product;
import apps.sffa.com.ainaki.webservice.API;
import apps.sffa.com.ainaki.webservice.ProductListWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diako on 21/05/2018.
 */

public class ProductsListActivity extends AppCompatActivity {

    RecyclerView recItems;
    TextView txtTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Bundle extras = getIntent().getExtras();

        if (extras != null && !extras.isEmpty()) {

            final String category = extras.getString("CATEGORY");
            final String filter = extras.getString("FILTER");
            final String title = extras.getString("TITLE");

            recItems = (RecyclerView) findViewById(R.id.recItems);
            txtTitle = (TextView) findViewById(R.id.txtTitle);

            txtTitle.setText(title);
            recItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            recItems.setNestedScrollingEnabled(false);
           // fetchProductsData(recItems, category, filter);
            recItems.setAdapter(new ProductAdapter(ProductsListActivity.this,initProducts(null)));
        } else {
            finish();
            Toast.makeText(ProductsListActivity.this, "Error in init extras", Toast.LENGTH_SHORT).show();
        }


    }

    private Retrofit initRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    private ArrayList<Product> initProducts (String type){

        ArrayList<Product>products=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            products.add(new Product(i,"product #"+i));

        }
        return products;
    }

    private void fetchProductsData(final RecyclerView recItems, String category, String filter) {

        ProductListWebService api = initRetrofit().create(ProductListWebService.class);


        Call<List<Product>> callProducts = api.getProducts(category, filter);

        callProducts.enqueue(new Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.body() != null) {
                    if (!response.body().isEmpty()) {
                        ArrayList<Product> products = new ArrayList<>();
                        products.addAll(response.body());
                        recItems.setAdapter(new ProductAdapter(getApplicationContext(), products));
                    } else
                        Toast.makeText(ProductsListActivity.this, "This is Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductsListActivity.this, "This is Error onFailure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public ArrayList<Product> initItems() {
        ArrayList<Product> items = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            items.add(new Product(i, "Product Name #" + i));
        }
        return items;
    }


}
