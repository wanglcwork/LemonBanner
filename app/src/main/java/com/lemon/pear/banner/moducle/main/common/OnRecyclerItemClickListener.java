package com.lemon.pear.banner.moducle.main.common;

import android.view.View;

/**
 * Created by lining on 2016/7/1.
 */
public interface OnRecyclerItemClickListener {
    void onItemClick(View view, int position);

    void onItemLongClick(View view, int position);
}