package com.lemon.pear.banner.moducle.main.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lemon.pear.banner.R;
import com.lemon.pear.banner.base.BasicActivity;
import com.lemon.pear.banner.moducle.main.DividerListItemDecoration;
import com.lemon.pear.banner.moducle.main.adapter.MainListAdapter;
import com.lemon.pear.banner.moducle.main.common.OnRecyclerItemClickListener;
import com.lemon.pear.banner.moducle.main.entity.BannerModel;
import com.lemon.pear.banner.moducle.main.view.BannerView;
import com.lemon.pear.banner.view.CanRefreshLayout;
import com.lemon.pear.banner.moducle.main.view.CanHeaderFooter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BasicActivity implements CanRefreshLayout.OnRefreshListener, CanRefreshLayout.OnLoadMoreListener {
    @BindView(R.id.bannerView)
    BannerView bannerView;
    @BindView(R.id.can_content_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    CanRefreshLayout refresh;
    @BindView(R.id.header)
    CanHeaderFooter header;

    private MainListAdapter mAdapter;

    private List<BannerModel> dataList;//数据集

    private String[] bannerDes = new String[]{"图片0", "图片1", "图片2", "图片3"};

    private String[] bannerUrls = new String[]{"http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang.jpg",
            "http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang-004.jpg",
            "http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang-009.jpg",
            "http://img.ivsky.com/img/tupian/pre/201608/30/ertong_xiaoxiang-016.jpg"};

    private List<String> mDatas;

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
        initBanner();
        initListView();
    }

    private void initBanner() {
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

    private void initListView() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerListItemDecoration(activity,
                DividerListItemDecoration.VERTICAL_LIST));
        mAdapter = new MainListAdapter(activity, mDatas, R.layout.item_list_view);
        recyclerView.setAdapter(mAdapter);
        refresh.setOnLoadMoreListener(this);
        refresh.setOnRefreshListener(this);
        header.attachTo(recyclerView, true);
        mAdapter.setOnItemClickLitener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, mDatas.get(position) + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    protected void widgetClick(View v) {

    }

    @Override
    public void onLoadMore() {
        refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.loadMoreComplete();
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        refresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.refreshComplete();
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bannerView.cancel();
    }
}
