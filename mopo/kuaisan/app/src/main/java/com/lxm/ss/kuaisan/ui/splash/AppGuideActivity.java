package com.lxm.ss.kuaisan.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.model.AppShowData;
import com.lxm.ss.kuaisan.ui.main.MainActivity;
import com.lxm.ss.kuaisan.ui.web.WebViewActivity;

import java.util.ArrayList;

import club.fromfactory.baselibrary.utils.StringUtils;

public class AppGuideActivity extends BaseActivity {

    private ViewPager mViewPager ;
    private  TextView mTxtNext ;

    private int maxlen = 3;
    private ImageView[] ivs;
    /**
     * 引导页图片容器
     */
    final ArrayList<View> views = new ArrayList<View>();// 放置图片的

    private int currIndex = 0 ;


    public static void launchActivity(Context context){
        Intent intent = new Intent(context,AppGuideActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_guide);
        initView();
        initData();
    }

    private void initView() {

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        findViewById(R.id.app_guide_ly_next).setOnClickListener(mOnClickListener);
        mTxtNext = (TextView) findViewById(R.id.app_guide_txt_next);
    }

    private void initData() {

        ivs = new ImageView[maxlen];
        for (int i = 0; i < maxlen; i++) {
            // 图片数据放在ivs里面
            ivs[i] = new ImageView(this);
            ivs[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            ivs[i].setLayoutParams(new LinearLayout.LayoutParams(mWidth, mHeight));
            switch (i) {
                case 0:
                    ivs[i].setImageResource(R.mipmap.page01_new);
                    break;
                case 1:
                    ivs[i].setImageResource(R.mipmap.page02_new);
                    break;
                case 2:
                    ivs[i].setImageResource(R.mipmap.page03_new);
                    break;
                default:
                    break;
            }

            // 图片
            LinearLayout view = (LinearLayout) LayoutInflater.from(
                    getApplicationContext()).inflate(
                    R.layout.just_linearlayout_center, null);
            view.addView(ivs[i]);
            views.add(view);

        }


        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public void destroyItem(View container, int position, Object o) {
                ((ViewPager) container).removeView(views.get(position));
            }

            @Override
            public Object instantiateItem(View container, int position) {
                ((ViewPager) container).addView(views.get(position));
                return views.get(position);
            }
        };
        mViewPager.setAdapter(mPagerAdapter);

        // 设置当前页面
        mViewPager.setCurrentItem(currIndex);

    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.app_guide_ly_next:

                    if (mViewPager.getCurrentItem() == views.size() - 1){
                        getMySwitch();
                    }else {
                        currIndex++;
                        mViewPager.setCurrentItem(currIndex);
                    }

                    break;
                default:
                    break;
            }
        }
    };


    private void getMySwitch() {

        MyOkHttp.getInstance().getMySwitchStatus(  new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);

                if (o != null) {
                    String str = (String) o;
                    Zlog.ii("lxm getMySwitch:" + str);

                    str = StringUtils.matchStrString("index1=(\\d{1})",str);

                    if (StringUtils.isNull(str)){
                        getStatus();
                    }else {
                        try {
                            String[] split = str.trim().split("=");
                            Zlog.ii("lxm getMySwitch:2 " +str + " " );
                            if (split.length == 2 && split[1].equals("1")) {
                                enterMain();
                            }else {
                                getStatus();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            getStatus();
                        }
                    }

                }else {
                    getStatus();
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                getStatus();
            }
        });
    }

    private void getStatus() {

        TypeReference typeReference = new TypeReference<AppShowData>(){};
        MyOkHttp.getInstance().getAppShowStatus(Constants.APPSHOW_ADID, typeReference, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);

                if (o != null) {
                    AppShowData appShowData = (AppShowData) o;

                    Zlog.ii("lxm getStatus:" + appShowData);
                    if (AppShowData.STATUS_JUMPT_WEB.equals(appShowData.getStatus())){
                        enterWebPage(appShowData.getUrl());
                    }else {
                        enterMain();
                    }
                }else {
                    enterMain();
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                enterMain();
            }
        });
    }

    private void enterMain() {
        MainActivity.launchActivity(AppGuideActivity.this);
        finish();
    }
    private void enterWebPage(String url) {
        WebViewActivity.launchActivity(AppGuideActivity.this,url);
        finish();
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageSelected(int arg0) {
            if (arg0 == 0) {

            } else if (arg0 == maxlen - 1) {
                mTxtNext.setText("完成");
            } else {

            }

            currIndex = arg0;// 设置当前View

        }
    }

}
