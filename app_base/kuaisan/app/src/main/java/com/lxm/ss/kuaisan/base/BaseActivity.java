package com.lxm.ss.kuaisan.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.lxm.ss.kuaisan.FFApplication;
import com.lxm.ss.kuaisan.Utils.DialogUtils;
import com.lxm.ss.kuaisan.Utils.PreferenceUtils;
import com.lxm.ss.kuaisan.Utils.Utils;
import com.lxm.ss.kuaisan.Utils.Zlog;

public abstract class BaseActivity extends FragmentActivity {

    public PreferenceUtils mPreferenceUtils;

    public String mAndroidId;

    private CustomDialog mCustomDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FFApplication.addActivity(this);
        mPreferenceUtils = PreferenceUtils.getInstance(this);
        mAndroidId = Utils.getAndroidId();

        mCustomDialog = DialogUtils.getInstance().getProgressDialog(this);
        
        Zlog.ii("lxm BaseActivity:onCreate");
    }
	  @Override
    protected void onResume() {
        super.onResume();
    }

    public void showOrHideSoftInputWindow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        Zlog.ii("lxm showOrHideSoftInputWindow:" +imm.isActive() + "  " );
    }

    public void showSoftInputWindow(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
        Zlog.ii("lxm showSoftInputWindow:" +getWindow().getAttributes().softInputMode ); //无效的参数
        if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            showOrHideSoftInputWindow();
        }
    }

    public void hideSoftInputWindow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromInputMethod(view.getWindowToken(),0);//無效无效
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),0);
    }
    public void hideSoftInputWindow(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    /**
     * 显示状态栏
     */
    private void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }
    /**
     * 隐藏状态栏
     */
    private void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
    }

    public void showBaseProgressDialog() {
      if (isAlive() && mCustomDialog != null) {
          mCustomDialog.show();
      }
    }
    public void hideBaseProgressDialog() {
        if (mCustomDialog != null && mCustomDialog.isShowing()) {
            mCustomDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Zlog.ii("lxm onActivityResult:" + requestCode + " " + resultCode);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FFApplication.removeActivity(this);
        if (mCustomDialog != null) {
            if (mCustomDialog.isShowing() ){
                mCustomDialog.dismiss();
            }
            mCustomDialog = null ;
        }
    }

    public boolean isAlive(){
        return (!isDestroyed() && !isFinishing());
    }
}
