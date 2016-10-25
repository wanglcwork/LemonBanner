package com.lemon.pear.banner.base;

import butterknife.ButterKnife;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * @description 所有Activity的基类
 *
 */
public abstract class BasicActivity extends Activity implements OnClickListener {

	protected BasicActivity activity;

	private boolean isSetStatusBar = false;// 是否沉浸状态栏

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
		if (isSetStatusBar) {
			steepStatusBar();
		}
		setContentView(getLayoutId());
		ButterKnife.bind(this);
		afterCreate(savedInstanceState);
	}

	/**
	 * 沉浸状态栏
	 */
	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void steepStatusBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	/** 获取布局 **/
	protected abstract int getLayoutId();

	/** 布局创建之后 **/
	protected abstract void afterCreate(Bundle savedInstanceState);

	/** View点击 **/
	protected abstract void widgetClick(View v);

	@Override
	public void onClick(View v) {
		widgetClick(v);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			View v = getCurrentFocus();
			if (isShouldHideInput(v, ev)) {

				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				if (imm != null) {
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
			return super.dispatchTouchEvent(ev);
		}
		// 必不可少，否则所有的组件都不会有TouchEvent了
		if (getWindow().superDispatchTouchEvent(ev)) {
			return true;
		}
		return onTouchEvent(ev);
	}

	public boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] leftTop = { 0, 0 };
			// 获取输入框当前的location位置
			v.getLocationInWindow(leftTop);
			int left = leftTop[0];
			int top = leftTop[1];
			int bottom = top + v.getHeight();
			int right = left + v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 点击的是输入框区域，保留点击EditText的事件
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
}
