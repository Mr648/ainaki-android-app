package apps.sffa.com.ainaki.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.ui.custom.CustomPicker;
import apps.sffa.com.ainaki.util.AndroidUtilities;

/**
 * Created by Diako on 17/06/2018.
 */

public class PickerDialog extends Dialog {


    private AppCompatActivity activty;
    private RelativeLayout relativeLayout;
    private AppCompatButton btnSelect;

    public PickerDialog(AppCompatActivity activty) {
        super(activty);
        this.activty = activty;
    }

    private static final int START_YAER = 1330;
    private static final int END_YAER = 1397;
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_picker_dialog);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        btnSelect = (AppCompatButton) findViewById(R.id.btnSelect);

        // ---------------------------------------------------------

        RelativeLayout.LayoutParams layoutParamsYear =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
        layoutParamsYear.addRule(RelativeLayout.ALIGN_PARENT_END);
        layoutParamsYear.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParamsYear.setMarginEnd((int) AndroidUtilities.convertDpToPixel(16, activty));

        // ---------------------------------------------------------

        RelativeLayout.LayoutParams layoutParamsMonth =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
        layoutParamsMonth.addRule(RelativeLayout.CENTER_IN_PARENT);

        // ---------------------------------------------------------

        RelativeLayout.LayoutParams layoutParamsDay =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
        layoutParamsDay.addRule(RelativeLayout.ALIGN_PARENT_START);
        layoutParamsDay.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParamsDay.setMarginStart((int) AndroidUtilities.convertDpToPixel(16, activty));

        // ---------------------------------------------------------

        final ArrayList<String> years = new ArrayList<>();
        final ArrayList<String> months = new ArrayList<>();
        final ArrayList<String> days = new ArrayList<>();

        for (int year = START_YAER; year <= END_YAER; year++) {
            years.add(Integer.toString(year));
        }

        for (String month :
                activty.getResources().getStringArray(R.array.months)) {
            months.add(month);
        }

        for (int day = START_DAY; day <= END_DAY; day++) {
            days.add(Integer.toString(day));
        }

        // ---------------------------------------------------------

        final CustomPicker yearPicker = new CustomPicker(activty, years);
        final CustomPicker monthPicker = new CustomPicker(activty, months);
        final CustomPicker dayPicker = new CustomPicker(activty, days);
        // ---------------------------------------------------------

        relativeLayout.addView(yearPicker, layoutParamsYear);
        relativeLayout.addView(monthPicker, layoutParamsMonth);
        relativeLayout.addView(dayPicker, layoutParamsDay);


        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activty, "Selected Date: " + years.get(yearPicker.getCurrentItem())+"/"+months.get(monthPicker.getCurrentItem())+"/"+days.get(dayPicker.getCurrentItem()), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
