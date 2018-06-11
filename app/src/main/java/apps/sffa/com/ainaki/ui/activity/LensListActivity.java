package apps.sffa.com.ainaki.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.LensAdapter;
import apps.sffa.com.ainaki.model.Lens;

/**
 * Created by Diako on 21/05/2018.
 */

public class LensListActivity extends AppCompatActivity {
    private RecyclerView recLensList;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens_list);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("لیست لنزها");

        recLensList = (RecyclerView) findViewById(R.id.recLensList);
        recLensList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recLensList.setNestedScrollingEnabled(false);
        recLensList.setAdapter(new LensAdapter(getApplicationContext(), initLenses()));
        recLensList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public ArrayList<Lens> initLenses() {
        ArrayList<Lens> lenses = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            lenses.add(new Lens());

        }
        return lenses;
    }
}
