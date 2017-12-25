package com.lxm.ss.kuaisan.base;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxm.ss.kuaisan.Utils.DialogUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.ui.main.DetailWebViewActivity;
import com.lxm.ss.kuaisan.ui.main.IntoActivity;
import com.lxm.ss.kuaisan.ui.main.ParseWebViewContentActivity;

/**
 * fragment基类
 */

public abstract class BaseFragment extends Fragment {
    private CustomDialog mCustomDialog ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Zlog.ii("lxm BaseFragment:onCreate");
        mCustomDialog = DialogUtils.getInstance().getProgressDialog(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Zlog.ii("lxm BaseFragment:onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Zlog.ii("lxm BaseFragment onResume:" );
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Zlog.ii("lxm BaseFragment onHiddenChanged:" + hidden);
        if (!hidden) {
        }else {

        }
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


    public void enterIntoActivity(String url ) {
        IntoActivity.launchActivity(getActivity(),url);
    }


    public void enterLocalWebView(String url) {
        DetailWebViewActivity.launchActivity(getActivity(),url);
    }
    public void enterLocalWebView(String url,String content) {
        ParseWebViewContentActivity.launchActivity(getActivity(),url,content);
    }
    public void enterLocalWebView(String url,String content,String imgUrl) {
        ParseWebViewContentActivity.launchActivity(getActivity(),url,content,imgUrl );
    }
    public void enterLocalWebView(String url,String content,int imgUrl) {
        ParseWebViewContentActivity.launchActivity(getActivity(),url,content,imgUrl );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Zlog.ii("lxm basefragment onActivityResult:" + requestCode + " " + resultCode);
    }

    public boolean isAlive() {

        return isAdded() && !isHidden();
    }

}
