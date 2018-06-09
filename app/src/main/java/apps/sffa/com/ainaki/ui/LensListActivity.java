package apps.sffa.com.ainaki.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.LensAdapter;
import apps.sffa.com.ainaki.model.Lens;

/**
 * Created by Diako on 21/05/2018.
 */

public class LensListActivity extends AppCompatActivity {
    RecyclerView recLensList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lens_list);
        recLensList=(RecyclerView) findViewById(R.id.recLensList);
        recLensList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        recLensList.setAdapter(new LensAdapter(getApplicationContext(), initLenses()));

    }
    public ArrayList<Lens> initLenses(){
        ArrayList<Lens> lenses= new ArrayList<>();
        for (int i=1;i<=20;i++){
            lenses.add(new Lens());

        }
        return lenses;
    }
}
