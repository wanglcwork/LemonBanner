package com.lemon.pear.banner.moducle.main.activity;

import android.os.Bundle;
import android.view.View;

import com.lemon.pear.banner.R;
import com.lemon.pear.banner.base.BasicActivity;
import com.lemon.pear.banner.moducle.main.entity.BannerModel;
import com.lemon.pear.banner.moducle.main.view.BannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BasicActivity {
    @BindView(R.id.bannerView)
    BannerView bannerView;

    private List<BannerModel> dataList;//数据集

    private String[] bannerDes = new String[]{"图片0", "图片1", "图片2","图片3"};

    private String[] bannerUrls = new String[]{"http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang.jpg",
    "http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang-004.jpg",
    "http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang-009.jpg",
    "http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang-016.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.aty_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        dataList = new ArrayList<>();
        for (int i = 0; i < bannerUrls.length; i++) {
            BannerModel model = new BannerModel();
            model.setIamgeDes(bannerDes[i]);
            model.setImageUrl(bannerUrls[i]);
            dataList.add(model);
        }
        bannerView.setData(dataList);
        bannerView.startScroll();
    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bannerView.cancel();
    }
}
