package com.lxm.ss.ssc.base;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxm.ss.ssc.Utils.Zlog;
import com.lxm.ss.ssc.ui.main.DetailWebViewActivity;
import com.lxm.ss.ssc.ui.main.IntoActivity;
import com.lxm.ss.ssc.ui.main.ParseWebViewContentActivity;

/**
 * fragment基类
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Zlog.ii("lxm BaseFragment:onCreate");
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
