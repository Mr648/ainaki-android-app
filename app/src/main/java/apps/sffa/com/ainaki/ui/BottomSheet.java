package apps.sffa.com.ainaki.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import apps.sffa.com.ainaki.R;


/**
 * Created by Diako on 23/05/2018.
 */

public class BottomSheet extends AppCompatActivity {

    TextView txtFilterType;
    BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttom_include_sheet);
        //  ButterKnife.bind(this);
        txtFilterType = (TextView) findViewById(R.id.txtFilterType);
        txtFilterType.setText(getResources().getString(R.string.label_choose_face_shape));
        LinearLayout layoutButtomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        final ImageView imgButtomSheet = (ImageView) findViewById(R.id.imgButtomSheet);
        imgButtomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    imgButtomSheet.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);

                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    imgButtomSheet.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                }
            }
        });
        sheetBehavior = BottomSheetBehavior.from(layoutButtomSheet);

        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        imgButtomSheet.setImageResource(R.drawable.ic_keyboard_arrow_down_black_18dp);
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        imgButtomSheet.setImageResource(R.drawable.ic_keyboard_arrow_up_black_18dp);

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


//        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
//        tr.add(new )
    }

    /**
     * manually opening / closing bottom sheet on button click
     */


}

