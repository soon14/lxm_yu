package com.lxm.ss.kuaisan.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ImageUtils;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import club.fromfactory.baselibrary.utils.StringUtils;
import club.fromfactory.fresco.view.FrescoImageView;

/**
 * 含有图片的内容
 */
public class ParseWebViewContentActivity extends BaseActivity {

    private CustomTitleLinearlayout mCtlTitle;

    private  static final String INTENT_IMAGE_LOCAL_URL  = "intent_image_local_url";
    private  static final String INTENT_IMAGE_WEB_URL  = "intent_image_web_url";

    private TextView mTxtContent ;
    private TextView mTxtMore ;

    private ImageView mImgLoacal ;
    private FrescoImageView mImgWeb ;

    private String mImgWebUrl ;

    private int mImgLocalRes ;

    private String mCurrentUrl;

    private String mContent ;
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url) {
        if (url == null || url.length() == 0) {
            return;
        }
        Intent intent = new Intent(context, ParseWebViewContentActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        context.startActivity(intent);
    }
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url,String content) {
        if (url == null || url.length() == 0) {
            return;
        }
        Intent intent = new Intent(context, ParseWebViewContentActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        intent.putExtra(Constants.INTENT_CONTENT, content);
        context.startActivity(intent);
    }
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url,String content , String imgUrl) {
        if (url == null || url.length() == 0) {
            return;
        }
        Intent intent = new Intent(context, ParseWebViewContentActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        intent.putExtra(Constants.INTENT_CONTENT, content);
        intent.putExtra(INTENT_IMAGE_WEB_URL, imgUrl);
        context.startActivity(intent);
    }
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url,String content ,int imgUrl) {
        if (url == null || url.length() == 0) {
            return;
        }
        Intent intent = new Intent(context, ParseWebViewContentActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        intent.putExtra(Constants.INTENT_CONTENT, content);
        intent.putExtra(INTENT_IMAGE_LOCAL_URL, imgUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_web_view_content);
        Intent intent = getIntent();
        mCurrentUrl = intent.getStringExtra(Constants.INTENT_URL);
        mContent = intent.getStringExtra(Constants.INTENT_CONTENT);
        mImgWebUrl = intent.getStringExtra(INTENT_IMAGE_WEB_URL);
        mImgLocalRes = intent.getIntExtra(INTENT_IMAGE_LOCAL_URL , -1);

        initView();
        initData();
    }

    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mTxtContent = (TextView) findViewById(R.id.txt_content);

        mImgLoacal = (ImageView) findViewById(R.id.img_local);
        mImgWeb = (FrescoImageView) findViewById(R.id.img_web);
        mTxtMore = (TextView) findViewById(R.id.txt_more);
        mImgWeb.setOnClickListener(mOnClickListener);
        mImgLoacal.setOnClickListener(mOnClickListener);
        mTxtMore.setOnClickListener(mOnClickListener);
    }

    private void initData() {

        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                goback();
            }
        });
        mTxtMore.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        mTxtMore.getPaint().setAntiAlias(true);//抗锯齿
        mTxtContent.setText(mContent == null ?"":mContent);

        if (StringUtils.isNotBlank(mImgWebUrl)) {
            mImgWeb.setVisibility(View.VISIBLE);
            ImageUtils.loadImage(mImgWeb,mImgWebUrl,false,R.mipmap.icon_logo);
        }else {
            mImgWeb.setVisibility(View.GONE);
        }

        if (mImgLocalRes != -1) {
            mImgLoacal.setVisibility(View.VISIBLE);
            mImgLoacal.setImageResource(mImgLocalRes);
        }else {
            mImgLoacal.setVisibility(View.GONE);

        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           switch (v.getId()){

               case R.id.img_local:
               case R.id.img_web:
               case R.id.txt_more:
                   try {
                       startActivity(new Intent(Intent.ACTION_VIEW,
                               Uri.parse(mCurrentUrl)));
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   break;
               default:
                   break;
           }

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 当keyCode等于退出事件值时
            goback();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    private void goback() {

        finish();
    }

}
