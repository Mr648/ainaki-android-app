package apps.sffa.com.ainaki.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ramotion.foldingcell.FoldingCell;

import apps.sffa.com.ainaki.R;

/**
 * Created by mr-code on 5/24/2018.
 */

public class FoldingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.folding);
        // get our folding cell
        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);

        // attach click listener to folding cell
        fc.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });
    }

}
