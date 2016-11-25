package com.example.administrator.demotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/9/30.
 */
public class BannerActivity extends AppCompatActivity {
    private Banner banner;
    private String[] images;
    private Integer[] image2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        initData();
        initView();
    }

    private void initData() {
        images = new String[]{
                "http://s3.mingxingyichu.cn/group6/M00/AE/2F/wKgBjVfNQEyASrljAALE86r9d_U589.jpg?imageMogr2?imageMogr2?imageMogr2",
                "http://s0.mingxingyichu.cn/group5/M00/6B/1E/wKgBf1fOermAemBaAAI8p26-TxQ559.jpg?imageMogr2?imageMogr2?imageMogr2",
                "http://s6.mingxingyichu.cn/group6/M00/AE/1C/wKgBjFfNL3qAOcPrAAF-KXNTTgo759.jpg?imageMogr2?imageMogr2?imageMogr2",
                "http://s3.mingxingyichu.cn/group6/M00/AE/55/wKgBjVfOYOuAOkuFAAFS1Oz5VZc216.jpg?imageMogr2?imageMogr2?imageMogr2",
                "http://s6.mingxingyichu.cn/group6/M00/AE/49/wKgBjFfOXyqATirRAAMEd6BpW94137.jpg?imageMogr2?imageMogr2?imageMogr2"};
        image2=new Integer[]{R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    }

    private void initView() {
        banner= (Banner) findViewById(R.id.banner);
        banner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
        //设置banner的默认动画,github查询方法
        GlideImageLoader  imgLoader=new GlideImageLoader();
        banner.setImageLoader(imgLoader);
//        banner.setImageLoader
        //0-7:center,center_crop,center_inside,fit_center,fit_end,fit_start,fit_xy,martrix
        banner.addScaleType(3);
        banner.setBannerAnimation(Transformer.Stack);
        banner.setImages(Arrays.asList(images));
        banner.start();
    }

}
