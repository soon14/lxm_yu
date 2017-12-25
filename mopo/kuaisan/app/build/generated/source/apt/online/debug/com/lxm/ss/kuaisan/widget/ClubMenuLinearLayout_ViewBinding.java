// Generated code from Butter Knife. Do not modify!
package com.lxm.ss.kuaisan.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lxm.ss.kuaisan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ClubMenuLinearLayout_ViewBinding implements Unbinder {
  private ClubMenuLinearLayout target;

  private View view2131558661;

  private View view2131558665;

  private View view2131558669;

  @UiThread
  public ClubMenuLinearLayout_ViewBinding(ClubMenuLinearLayout target) {
    this(target, target);
  }

  @UiThread
  public ClubMenuLinearLayout_ViewBinding(final ClubMenuLinearLayout target, View source) {
    this.target = target;

    View view;
    target.menuOneImg = Utils.findRequiredViewAsType(source, R.id.menu_one_img, "field 'menuOneImg'", ImageView.class);
    target.menuOneContainer = Utils.findRequiredViewAsType(source, R.id.menu_one_container, "field 'menuOneContainer'", LinearLayout.class);
    target.menuOneTxt = Utils.findRequiredViewAsType(source, R.id.menu_one_txt, "field 'menuOneTxt'", TextView.class);
    view = Utils.findRequiredView(source, R.id.menu_one, "field 'menuOne' and method 'onViewClicked'");
    target.menuOne = Utils.castView(view, R.id.menu_one, "field 'menuOne'", LinearLayout.class);
    view2131558661 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.menuTwoImg = Utils.findRequiredViewAsType(source, R.id.menu_two_img, "field 'menuTwoImg'", ImageView.class);
    target.menuTwoContainer = Utils.findRequiredViewAsType(source, R.id.menu_two_container, "field 'menuTwoContainer'", LinearLayout.class);
    target.menuTwoTxt = Utils.findRequiredViewAsType(source, R.id.menu_two_txt, "field 'menuTwoTxt'", TextView.class);
    view = Utils.findRequiredView(source, R.id.menu_two, "field 'menuTwo' and method 'onViewClicked'");
    target.menuTwo = Utils.castView(view, R.id.menu_two, "field 'menuTwo'", LinearLayout.class);
    view2131558665 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.menuThreeImg = Utils.findRequiredViewAsType(source, R.id.menu_three_img, "field 'menuThreeImg'", ImageView.class);
    target.menuThreeContainer = Utils.findRequiredViewAsType(source, R.id.menu_three_container, "field 'menuThreeContainer'", LinearLayout.class);
    target.menuThreeTxt = Utils.findRequiredViewAsType(source, R.id.menu_three_txt, "field 'menuThreeTxt'", TextView.class);
    view = Utils.findRequiredView(source, R.id.menu_three, "field 'menuThree' and method 'onViewClicked'");
    target.menuThree = Utils.castView(view, R.id.menu_three, "field 'menuThree'", LinearLayout.class);
    view2131558669 = view;
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
    ClubMenuLinearLayout target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.menuOneImg = null;
    target.menuOneContainer = null;
    target.menuOneTxt = null;
    target.menuOne = null;
    target.menuTwoImg = null;
    target.menuTwoContainer = null;
    target.menuTwoTxt = null;
    target.menuTwo = null;
    target.menuThreeImg = null;
    target.menuThreeContainer = null;
    target.menuThreeTxt = null;
    target.menuThree = null;

    view2131558661.setOnClickListener(null);
    view2131558661 = null;
    view2131558665.setOnClickListener(null);
    view2131558665 = null;
    view2131558669.setOnClickListener(null);
    view2131558669 = null;
  }
}
