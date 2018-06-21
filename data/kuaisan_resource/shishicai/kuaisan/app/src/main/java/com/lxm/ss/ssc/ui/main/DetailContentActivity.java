package com.lxm.ss.ssc.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lxm.ss.ssc.R;
import com.lxm.ss.ssc.Utils.Zlog;
import com.lxm.ss.ssc.base.BaseActivity;
import com.lxm.ss.ssc.constant.Constants;
import com.lxm.ss.ssc.widget.CustomTitleLinearlayout;

import club.fromfactory.baselibrary.utils.StringUtils;

public class DetailContentActivity extends BaseActivity {

    private CustomTitleLinearlayout mCtlTitle;

    private TextView mTxtCont ;

    private String mContent ;

    /**
     * 启动详情页面
     *
     * @param content 需要打开页面的content。
     */
    public static void launchActivity(Context context, String content) {
        if (StringUtils.isNull(content)) {
            return;
        }
        Zlog.ii("lxm initView: launchActivity  " + content);
        Intent intent = new Intent(context, DetailContentActivity.class);
        intent.putExtra(Constants.INTENT_CONTENT, content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);
        mContent = getIntent().getStringExtra(Constants.INTENT_CONTENT);
        initView();
        initData();
    }

    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mTxtCont = (TextView) findViewById(R.id.detail_content_txt_content);

        Zlog.ii("lxm initView:" + mContent);
        mTxtCont.setText(mContent == null ?"":mContent);
    }

    private void initData() {
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                finish();
            }
        });
    }
}
