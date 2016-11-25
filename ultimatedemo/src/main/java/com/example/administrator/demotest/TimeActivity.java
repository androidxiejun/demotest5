package com.example.administrator.demotest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;

public class TimeActivity extends AppCompatActivity {
    private TextView mTv;
    private int year,month,day,hour,menute,second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        initView();
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Calendar calendar=Calendar.getInstance();
            year= calendar.get(Calendar.YEAR);
            month=calendar.get(Calendar.MONTH);
            day=calendar.get(Calendar.DAY_OF_MONTH);
            hour=calendar.get(Calendar.HOUR);
            menute=calendar.get(Calendar.MINUTE);
            second = calendar.get(Calendar.SECOND);
            mTv.setText(year+"-"+month+"-"+day+"-"+hour+"-"+menute+"-"+second);
            mHandler.sendEmptyMessageDelayed(1,1000);
        }
    };
    private void initView() {
        mTv= (TextView) findViewById(R.id.time_txt);
        mHandler.sendEmptyMessage(1);
    }

}
