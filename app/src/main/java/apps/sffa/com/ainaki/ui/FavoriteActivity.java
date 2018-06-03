package apps.sffa.com.ainaki.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.FavoriteAdapter;
import apps.sffa.com.ainaki.model.Favorite;

/**
 * Created by Diako on 21/05/2018.
 */

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView recFavorite;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recFavorite=(RecyclerView) findViewById(R.id.recFavorite);
        recFavorite.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        recFavorite.setAdapter(new FavoriteAdapter(getApplicationContext(),initFavorite()));

    }
    public ArrayList<Favorite> initFavorite(){
        ArrayList<Favorite> Favorite= new ArrayList<>();
        for (int i=1;i<=20;i++){
            Favorite.add(new Favorite());

        }
        return Favorite;
    }
}
