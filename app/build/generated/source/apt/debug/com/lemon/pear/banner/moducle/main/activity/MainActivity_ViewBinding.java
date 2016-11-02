// Generated code from Butter Knife. Do not modify!
package com.lemon.pear.banner.moducle.main.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lemon.pear.banner.R;
import com.lemon.pear.banner.moducle.main.view.BannerView;
import com.lemon.pear.banner.moducle.main.view.CanHeaderFooter;
import com.lemon.pear.banner.view.CanRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.bannerView = Utils.findRequiredViewAsType(source, R.id.bannerView, "field 'bannerView'", BannerView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.can_content_view, "field 'recyclerView'", RecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", CanRefreshLayout.class);
    target.header = Utils.findRequiredViewAsType(source, R.id.header, "field 'header'", CanHeaderFooter.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.bannerView = null;
    target.recyclerView = null;
    target.refresh = null;
    target.header = null;

    this.target = null;
  }
}
