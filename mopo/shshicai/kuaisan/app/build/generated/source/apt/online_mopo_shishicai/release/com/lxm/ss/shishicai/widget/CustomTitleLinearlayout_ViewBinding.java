// Generated code from Butter Knife. Do not modify!
package com.lxm.ss.shishicai.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lxm.ss.shishicai.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomTitleLinearlayout_ViewBinding implements Unbinder {
  private CustomTitleLinearlayout target;

  private View view2131558653;

  private View view2131558652;

  private View view2131558650;

  private View view2131558654;

  private View view2131558651;

  private View view2131558655;

  @UiThread
  public CustomTitleLinearlayout_ViewBinding(CustomTitleLinearlayout target) {
    this(target, target);
  }

  @UiThread
  public CustomTitleLinearlayout_ViewBinding(final CustomTitleLinearlayout target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.txt_title_left, "field 'txtTitleLeft' and method 'onViewClicked'");
    target.txtTitleLeft = Utils.castView(view, R.id.txt_title_left, "field 'txtTitleLeft'", TextView.class);
    view2131558653 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.img_left, "field 'imgLeft' and method 'onViewClicked'");
    target.imgLeft = Utils.castView(view, R.id.img_left, "field 'imgLeft'", ImageView.class);
    view2131558652 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.txt_title_center, "field 'txtTitleCenter' and method 'onViewClicked'");
    target.txtTitleCenter = Utils.castView(view, R.id.txt_title_center, "field 'txtTitleCenter'", TextView.class);
    view2131558650 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ly_right, "field 'lyRight' and method 'onViewClicked'");
    target.lyRight = Utils.castView(view, R.id.ly_right, "field 'lyRight'", LinearLayout.class);
    view2131558654 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.logo, "field 'logo' and method 'onViewClicked'");
    target.logo = Utils.castView(view, R.id.logo, "field 'logo'", ImageView.class);
    view2131558651 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.layout_title_img_right, "field 'layoutTitleImgRight' and method 'onViewClicked'");
    target.layoutTitleImgRight = Utils.castView(view, R.id.layout_title_img_right, "field 'layoutTitleImgRight'", ImageView.class);
    view2131558655 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomTitleLinearlayout target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtTitleLeft = null;
    target.imgLeft = null;
    target.txtTitleCenter = null;
    target.lyRight = null;
    target.logo = null;
    target.layoutTitleImgRight = null;

    view2131558653.setOnClickListener(null);
    view2131558653 = null;
    view2131558652.setOnClickListener(null);
    view2131558652 = null;
    view2131558650.setOnClickListener(null);
    view2131558650 = null;
    view2131558654.setOnClickListener(null);
    view2131558654 = null;
    view2131558651.setOnClickListener(null);
    view2131558651 = null;
    view2131558655.setOnClickListener(null);
    view2131558655 = null;
  }
}
