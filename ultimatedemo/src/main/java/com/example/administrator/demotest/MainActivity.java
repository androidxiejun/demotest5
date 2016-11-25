package com.example.administrator.demotest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.banner_btn:
                Intent intent=new Intent(this,BannerActivity.class);
                startActivity(intent);
                break;
            case R.id.ultimate_btn:
                Intent intent2=new Intent(this,UtimateActivity.class);
                startActivity(intent2);
                break;
            case R.id.birthday_btn:
                Intent intent3=new Intent(this,BirthDayActivity.class);
                startActivity(intent3);
                break;
            case R.id.take_photo_btn:
                Intent intent4=new Intent(this,TakePhoto.class);
                startActivity(intent4);
                break;
            case R.id.time_btn:
                Intent intent5=new Intent(this,TimeActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
