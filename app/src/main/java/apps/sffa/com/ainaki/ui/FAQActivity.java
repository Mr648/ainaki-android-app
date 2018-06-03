package apps.sffa.com.ainaki.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.FAQAdapter;
import apps.sffa.com.ainaki.model.FAQ;

/**
 * Created by Diako on 19/05/2018.
 */

public class FAQActivity extends AppCompatActivity {
    RecyclerView recFAQ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        recFAQ=(RecyclerView) findViewById(R.id.recFAQ);
        recFAQ.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recFAQ.setAdapter(new FAQAdapter(getApplicationContext(),initFAQ()));

    }
    public ArrayList<FAQ> initFAQ(){
        ArrayList<FAQ> FAQ= new ArrayList<>();
        for (int i=1;i<=20;i++){
            FAQ.add(new FAQ("Title #"+i,"Answer #"+i,"Question #"+i));

        }
        return FAQ;
    }
}
