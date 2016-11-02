package com.lemon.pear.banner.moducle.main.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lemon.pear.banner.moducle.main.adapter.BannerAdapter;
import com.lemon.pear.banner.moducle.main.entity.BannerModel;
import com.lemon.pear.banner.utils.DisplayUtils;
import com.lemon.pear.banner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ning on 2016/10/25.
 */

public class BannerView extends RelativeLayout implements OnPageChangeListener {

    private Context context;//上下文引用

    private ViewPager vpBanner;//滑动布局

    private LinearLayout llDots;//圆圈布局

    private List<ImageView> dotList;//圆圈集引用,控制切换

    private int bannerSize = 0;//图片个数

    private int pointIndex = 0;// 圆圈标志位

    private Timer mTimer = new Timer();//计时器

    private TimerTask mTimerTask = new TimerTask() {//计时任务
        @Override
        public void run() {
            /**
             * Android在子线程更新UI的几种方法
             * Handler，AsyncTask,view.post,runOnUiThread
             */
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    vpBanner.setCurrentItem(vpBanner.getCurrentItem() + 1);
                }
            });
        }
    };

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    //初始化view
    private void initView() {
        View.inflate(context, R.layout.lay_banner_view, this);
        vpBanner = (ViewPager) findViewById(R.id.vpBanner);
        llDots = (LinearLayout) findViewById(R.id.llDots);
    }

    //初始化数据
    public void setData(List<BannerModel> dataList) {
        if (dataList != null && dataList.size() > 0) {
            bannerSize = dataList.size();
            dotList = new ArrayList<>();
            llDots.removeAllViews();
            for (int i = 0; i < bannerSize; i++) {
                // 设置圆圈点
                ImageView view = new ImageView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
                params.rightMargin = DisplayUtils.dip2px(context, 5);
                view.setBackgroundResource(R.drawable.dot_normal);
                //设置点的间距
                view.setLayoutParams(params);
                llDots.addView(view);
                dotList.add(view);
            }
            vpBanner.setAdapter(new BannerAdapter(context, dataList));
            vpBanner.addOnPageChangeListener(this);
            //取中间数来作为起始位置
            int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % bannerSize);
            vpBanner.setCurrentItem(index);
        }
    }

    //开始滚动
    public void startScroll() {
        mTimer.schedule(mTimerTask, 3000, 3000);
    }

    //取消滚动
    public void cancel() {
        mTimer.cancel();
    }

    @Override
    public void onPageSelected(int position) {
        int newPosition = position % bannerSize;
        dotList.get(pointIndex).setBackgroundResource(R.drawable.dot_normal);
        dotList.get(newPosition).setBackgroundResource(R.drawable.dot_focused);
        // 更新标志位
        pointIndex = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

}
