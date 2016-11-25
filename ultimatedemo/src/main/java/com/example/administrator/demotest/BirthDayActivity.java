package com.example.administrator.demotest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class BirthDayActivity extends AppCompatActivity implements View.OnClickListener{
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;
    private static int year,month,day,hour,minete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_day);
        initView();

    }

    private void initView() {
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hour=calendar.get(Calendar.HOUR);
        minete=calendar.get(Calendar.MINUTE);
        mDatePicker= (DatePicker) findViewById(R.id.date_picker);
        mTimePicker= (TimePicker) findViewById(R.id.time_picker);
        mDatePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                year=i;
                month=i1;
                day=i2;
            }
        });
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                hour=i;
                minete=i1;
            }
        });
    }

    @Override
    public void onClick(View view) {
    }
}
