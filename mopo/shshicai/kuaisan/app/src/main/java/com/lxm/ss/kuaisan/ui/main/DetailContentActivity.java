package com.lxm.ss.kuaisan.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import club.fromfactory.baselibrary.utils.StringUtils;

public class DetailContentActivity extends BaseActivity {

    private CustomTitleLinearlayout mCtlTitle;

    private TextView mTxtCont ;

    private String title ;

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
    /**
     * 启动详情页面
     *
     * @param content 需要打开页面的content。
     */
    public static void launchActivity(Context context, String content,String title) {
        if (StringUtils.isNull(content)) {
            return;
        }
        Zlog.ii("lxm initView: launchActivity  " + content);
        Intent intent = new Intent(context, DetailContentActivity.class);
        intent.putExtra(Constants.INTENT_CONTENT, content);
        intent.putExtra(Constants.INTENT_TITLE,title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);
        mContent = getIntent().getStringExtra(Constants.INTENT_CONTENT);
        title = getIntent().getStringExtra(Constants.INTENT_TITLE);
        initView();
        initData();
    }

    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mTxtCont = (TextView) findViewById(R.id.detail_content_txt_content);


    }

    private void initData() {
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                finish();
            }
        });

        if (StringUtils.isNotBlank(title)){
            mCtlTitle.setTitleCenter(title);
        }
        Zlog.ii("lxm initData:" + mContent);
        mTxtCont.setText(mContent == null ?"":mContent);
    }
}
