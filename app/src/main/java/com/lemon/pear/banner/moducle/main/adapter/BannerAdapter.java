package com.lemon.pear.banner.moducle.main.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lemon.pear.banner.moducle.main.entity.BannerModel;
import com.lemon.pear.banner.utils.ImageLoader;
import com.lemon.pear.banner.R;

import java.util.List;

/**
 * Created by ning on 2016/10/24.
 */

public class BannerAdapter extends PagerAdapter {

    private Context context;
    //数据源
    private List<BannerModel> dataList;

    private int dataSize = 1;//数据大小

    public BannerAdapter(Context context, List<BannerModel> dataList) {
        this.context = context;
        this.dataList = dataList;
        if (dataList != null && dataList.size() > 0) {
            dataSize = dataList.size();
        }
    }

    @Override
    public int getCount() {
        if (dataSize == 1) {
            return dataSize;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= dataSize;

        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_image, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivBanner);

        //加载图片
        ImageLoader.loadImage(context, dataList.get(position).getImageUrl(), imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
