package apps.sffa.com.ainaki.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import apps.sffa.com.ainaki.R;

/**
 * Created by Diako on 17/06/2018.
 */

public class DatePickerDialog extends Dialog {

    private Spinner spnYear;
    private Spinner spnMonth;
    private Spinner spnDay;

    private Button btnPick;

    private Date selectedDate;
    private Handler handler;

    private AppCompatActivity activty;

    public DatePickerDialog(AppCompatActivity activty) {
        super(activty);
        this.activty = activty;
        handler = new Handler();
        selectedDate = new Date();

    }

    public class Date {
        private int year;
        private int month;
        private int day;


        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;

        }

        @Override
        public String toString() {
            return this.getYear() + "/" + this.getMonth() + "/" + this.getDay();
        }
    }

    private DatePickedListener dateListener;

    private static final int START_YAER = 1330;
    private static final int END_YAER = 1397;
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    public void setDateListener(DatePickedListener dateListener) {
        this.dateListener = dateListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.date_time_picker);

        spnYear = (Spinner) findViewById(R.id.spnYear);
        spnMonth = (Spinner) findViewById(R.id.spnMonth);
        spnDay = (Spinner) findViewById(R.id.spnDay);

        btnPick = (Button) findViewById(R.id.btnPick);

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateListener.dateSelected(selectedDate);
            }
        });



        // Spinner's data!
        final ArrayList<String> years = new ArrayList<>();
        final ArrayList<String> months = new ArrayList<>();
        final ArrayList<String> days = new ArrayList<>();

        for (int year = START_YAER; year <= END_YAER; year++) {
            years.add(Integer.toString(year));
        }
        for (int day = START_DAY; day <= END_DAY; day++) {
            days.add(Integer.toString(day));
        }

        final String[] monthsArray = activty.getResources().getStringArray(R.array.months);
        months.addAll(Arrays.asList(monthsArray));


        // Building Adapters
        final ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(activty, android.R.layout.simple_spinner_item, years);
        final ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(activty, android.R.layout.simple_spinner_item, months);
        final ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(activty, android.R.layout.simple_spinner_item, days);


        // Setting Adapters
        spnYear.setAdapter(yearAdapter);
        spnMonth.setAdapter(monthAdapter);
        spnDay.setAdapter(daysAdapter);


        //Settinh Default Selection
        spnYear.setSelection(0);
        spnMonth.setSelection(0);
        spnDay.setSelection(0);

        // Setting onItem Selected For Spinners
        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedDate.setYear(Integer.parseInt(years.get(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0);
                selectedDate.setYear(Integer.parseInt(years.get(0)));

            }
        });

        spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5: {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                daysAdapter.clear();
                                for (int day = 1; day <= 31; day++) {
                                    daysAdapter.add(Integer.toString(day));
                                }
                                daysAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11: {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                daysAdapter.clear();
                                for (int day = 1; day <= 30; day++) {
                                    daysAdapter.add(Integer.toString(day));
                                }
                                daysAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    break;
                }

                selectedDate.setMonth(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0);
                selectedDate.setMonth(1);

            }
        });

        spnDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                selectedDate.setDay(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(0);
                selectedDate.setDay(1);

            }
        });

    }


    public interface DatePickedListener {
        void dateSelected(Date selectedDate);
    }
}
