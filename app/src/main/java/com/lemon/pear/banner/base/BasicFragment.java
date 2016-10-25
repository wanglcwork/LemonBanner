package com.lemon.pear.banner.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public abstract class BasicFragment extends Fragment implements OnClickListener {

	protected Activity activity;

	// Fragment被创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = getActivity();
	}

	// 初始化Fragment布局
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	// activity创建结束
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		afterCreate(savedInstanceState);
	}

	/** 获取布局 **/
	protected abstract int getLayoutId();

	/** 布局创建之后 **/
	protected abstract void afterCreate(Bundle savedInstanceState);

	/** View点击 **/
	protected abstract void widgetClick(View v);
	
	/** 更新View **/
	public abstract void updateView(); 

	@Override
	public void onClick(View v) {
		widgetClick(v);
	}
}
