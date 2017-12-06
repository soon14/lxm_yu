package com.lxm.ss.shishicai.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lxm.ss.shishicai.R;
import com.lxm.ss.shishicai.Utils.Zlog;
import com.lxm.ss.shishicai.base.BaseActivity;
import com.lxm.ss.shishicai.constant.Constants;
import com.lxm.ss.shishicai.http.MyOkHttp;
import com.lxm.ss.shishicai.http.OkHttpRequestListener;
import com.lxm.ss.shishicai.parse.model.ScreenReg;
import com.lxm.ss.shishicai.widget.CustomTitleLinearlayout;

import java.io.Serializable;
import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * 网络解析数据
 */
public class DetailParseWebContentActivity extends BaseActivity {

    public static final String REG_LIST = "reg_list" ;
    private CustomTitleLinearlayout mCtlTitle;

    private TextView mTxtCont ;

    private String mUrl ;
    private String mTitle ;

    private List<ScreenReg> screenRegList ;
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url) {
        if (StringUtils.isNull(url)) {
            return;
        }
        Zlog.ii("lxm initView: launchActivity  " + url);
        Intent intent = new Intent(context, DetailParseWebContentActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        context.startActivity(intent);
    }
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url,String title , List<ScreenReg> screenRegList) {
        if (StringUtils.isNull(url)) {
            return;
        }
        Zlog.ii("lxm initView: launchActivity  " + url);
        Intent intent = new Intent(context, DetailParseWebContentActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        intent.putExtra(Constants.INTENT_TITLE, title);
        intent.putExtra(REG_LIST, (Serializable) screenRegList);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_parse_web_content);
        mTitle = getIntent().getStringExtra(Constants.INTENT_TITLE);
        mUrl = getIntent().getStringExtra(Constants.INTENT_URL);
        screenRegList = (List<ScreenReg>) getIntent().getSerializableExtra(REG_LIST);
        initView();
        initData();
    }
    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mTxtCont = (TextView) findViewById(R.id.detail_content_txt_content);

    }

    private void initData() {
        if (StringUtils.isNotBlank(mTitle)){
            mCtlTitle.setTitleCenter(mTitle);
        }
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                finish();
            }
        });
        getHtmlString();
    }

    private void getHtmlString() {
        MyOkHttp.getInstance().getHtml(mUrl ,new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);

                if (o != null) {
                    String str = (String) o;
                    Zlog.ii("lxm parserHtml:" + str);


                    final String content = parseUrl(str);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTxtCont.setText(content);
                        }
                    });
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
            }
        });

    }

    private String parseUrl(String htmlString) {

        if (screenRegList != null && screenRegList.size() > 0){
            for (int i = 0; i < screenRegList.size(); i++) {
                ScreenReg screenReg = screenRegList.get(i);

                Zlog.ii("lxm parseUrl:" + screenReg);

                boolean screen = screenReg.isScreen();
                String regStr = screenReg.getRegStr();
                String replace = StringUtils.isNull(screenReg.getReplace()) == true ? "":screenReg.getReplace();
                if (screen){
                    htmlString = StringUtils.matchReplace(regStr,htmlString,replace);
                }else {
                    htmlString = StringUtils.matchStrString(regStr,htmlString);
                }
            }
            return htmlString ;

        }else {
            String reg1 = "\\s*|\t|\r|\n";
            String regMatch2 = "<title>(.*?)</title>|<divclass=\"help_t_int\">(.*?)</div></div><divclass=\"clear\"></div>";

            String regMatch3 = "<[^>]*>";

            htmlString =   StringUtils.matchReplace(reg1,htmlString);

            String result1 =   StringUtils.matchStrString(regMatch2,htmlString);

            result1 =   StringUtils.matchReplace(regMatch3,result1,"\n");
            Zlog.ii("lxm parserHtml:1" + result1);
            return  result1 ;
        }

    }




}
