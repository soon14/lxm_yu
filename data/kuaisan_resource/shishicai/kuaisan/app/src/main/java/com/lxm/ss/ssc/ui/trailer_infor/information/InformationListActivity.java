package com.lxm.ss.ssc.ui.trailer_infor.information;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lxm.ss.ssc.R;
import com.lxm.ss.ssc.base.BaseActivity;
import com.lxm.ss.ssc.widget.CustomTitleLinearlayout;

public class InformationListActivity extends BaseActivity {


    private CustomTitleLinearlayout mCtlTitle ;

    private TextView mTxtMore ;

    /**
     *
     */
    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, InformationListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_list);

        initView();

        initData();
    }


    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);

        mTxtMore = (TextView) findViewById(R.id.infor_list_txt_more);

        mTxtMore.setOnClickListener(mOnClickListener);

        findViewById(R.id.inform_list_ly_01).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_02).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_03).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_04).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_05).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_06).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_07).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_08).setOnClickListener(mOnClickListener);
        findViewById(R.id.inform_list_ly_09).setOnClickListener(mOnClickListener);

    }

    private void initData() {
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                super.clickLeft();
                finish();
            }
        });
        mTxtMore.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        mTxtMore.getPaint().setAntiAlias(true);//抗锯齿
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.trailer_list_txt_more:

                    break;
                case R.id.inform_list_ly_01:
                    enterInfor("http://zxwap.caipiao.163.com/","头条信息");
                    break;

                case R.id.inform_list_ly_02:
                    enterInfor("http://zxwap.caipiao.163.com/news","彩市新闻");
                    break;

                case R.id.inform_list_ly_03:
                    enterInfor("http://zxwap.caipiao.163.com/dongtai","近期动态");
                    break;

                case R.id.inform_list_ly_04:
                    enterInfor("http://zxwap.caipiao.163.com/hangye","行业信息");
                    break;

                case R.id.inform_list_ly_05:
                    enterInfor("http://zxwap.caipiao.163.com/yiyan","易眼金晴");
                    break;
                case R.id.inform_list_ly_06:
                    enterInfor("http://zxwap.caipiao.163.com/pl3","体彩其他");
                    break;
                case R.id.inform_list_ly_07:
                    enterInfor("http://zxwap.caipiao.163.com/3d","福彩其他");
                    break;
                case R.id.inform_list_ly_08:
                    enterInfor("http://zxwap.caipiao.163.com/world","国际足球");
                    break;
                case R.id.inform_list_ly_09:
                    enterInfor("http://zxwap.caipiao.163.com/china","中国足球");
                    break;

                default:
                    break;
            }
        }
    };

    private void enterInfor(String url ,String title) {
        InformationDetailListActivity.launchActivity(InformationListActivity.this,url,title);
    }



}
