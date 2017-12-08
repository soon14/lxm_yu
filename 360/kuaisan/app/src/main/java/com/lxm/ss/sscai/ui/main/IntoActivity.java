package com.lxm.ss.sscai.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lxm.ss.sscai.R;
import com.lxm.ss.sscai.Utils.Zlog;
import com.lxm.ss.sscai.base.BaseActivity;
import com.lxm.ss.sscai.constant.Constants;
import com.lxm.ss.sscai.parse.model.ScreenReg;
import com.lxm.ss.sscai.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

public class IntoActivity extends BaseActivity {





    private String mUrl ; //http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE.html

    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url) {
        if (StringUtils.isNull(url)) {
            return;
        }
        Zlog.ii("lxm initView: launchActivity  " + url);
        Intent intent = new Intent(context, IntoActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into);
        mUrl = getIntent().getStringExtra(Constants.INTENT_URL);
        initView();
    }

    private void initView() {
        CustomTitleLinearlayout mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        findViewById(R.id.into_01).setOnClickListener(mOnClickListener);
        findViewById(R.id.into_02).setOnClickListener(mOnClickListener);
        findViewById(R.id.into_03).setOnClickListener(mOnClickListener);

        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                super.clickLeft();
                finish();
            }
        });

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                //http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE.html

                case R.id.into_01:
                    enterParseWebContent(mUrl,"玩法规则");
//                    DetailParseWebContentActivity.launchActivity(IntoActivity.this,mUrl);
                    break;

//                http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE_2.html

                case R.id.into_02:
                    enterParseWebContent(urlChange(mUrl,"_2"),"玩法介绍");
//                    DetailParseWebContentActivity.launchActivity(IntoActivity.this,urlChange(mUrl,"_2"));
                    break;
//                http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE_3.html
                case R.id.into_03:
                    enterParseWebContent(urlChange(mUrl,"_3"),"奖项规则");
//                    DetailParseWebContentActivity.launchActivity(IntoActivity.this,urlChange(mUrl,"_3"));
                    break;

                default:
                    break;
            }
        }
    };

    private void enterParseWebContent(String url,String title) {

        String reg1 = "\\s*|\t|\r|\n";
        String regMatch2 = "<title>(.*?)</title>|<divclass=\"help_t_int\">(.*?)</div></div><divclass=\"clear\"></div>";

        String regMatch3 = "<[^>]*>";

        String regMatch5 = "\n\n";

        List<ScreenReg> screenRegList = new ArrayList<>() ;

        ScreenReg screenReg  = new ScreenReg();
        screenReg.setRegStr(reg1);
        screenReg.setReplace("");
        screenReg.setScreen(true);
        screenRegList.add(screenReg);

        screenReg  = new ScreenReg();
        screenReg.setRegStr(regMatch2);
        screenReg.setReplace("");
        screenReg.setScreen(false);
        screenRegList.add(screenReg);

        screenReg  = new ScreenReg();
        screenReg.setRegStr(regMatch3);
        screenReg.setReplace("\n");
        screenReg.setScreen(true);
        screenRegList.add(screenReg);

        screenReg  = new ScreenReg();
        screenReg.setRegStr(regMatch5);
        screenReg.setReplace("\n");
        screenReg.setScreen(true);
        screenRegList.add(screenReg);

        DetailParseWebContentActivity.launchActivity(IntoActivity.this,url,title,screenRegList);
    }


    private String urlChange(String url,String addStr) {

        Zlog.ii("lxm urlChange:" + url);

        String newUrl= url.substring(0,url.indexOf(".html"));
        Zlog.ii("lxm urlChange:" + newUrl);

        return newUrl +addStr + ".html" ;

    }







}
