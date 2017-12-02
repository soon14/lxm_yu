package com.lxm.ss.kuaisan.ui.trailer_infor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.ui.main.DetailContentActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import club.fromfactory.baselibrary.utils.StringUtils;

public class TrailerListActivity extends BaseActivity {


    private CustomTitleLinearlayout  mCtlTitle ;

    private TextView mTxtMore ;

    /**
     *
     */
    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, TrailerListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_list);

        initView();

        initData();
    }

    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);

        mTxtMore = (TextView) findViewById(R.id.trailer_list_txt_more);

        mTxtMore.setOnClickListener(mOnClickListener);

        findViewById(R.id.trailer_list_ly_ssq).setOnClickListener(mOnClickListener);
        findViewById(R.id.trailer_list_ly_dlt).setOnClickListener(mOnClickListener);
        findViewById(R.id.trailer_list_ly_jjzuqiu).setOnClickListener(mOnClickListener);
        findViewById(R.id.trailer_list_ly_jjlanqiu).setOnClickListener(mOnClickListener);

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
                case R.id.trailer_list_ly_ssq:
                    enterInfor("http://caipiao.163.com/m/redirectWapUrl.html?redirectUrl=http%3A%2F%2Fzxwap.caipiao.163.com%2Fssq%23fr%3Dapp","双色球");
                    break;
                case R.id.trailer_list_ly_dlt:
                    enterInfor("http://caipiao.163.com/m/redirectWapUrl.html?redirectUrl=http%3A%2F%2Fzxwap.caipiao.163.com%2Fdlt%23fr%3Dapp","大乐透");
                    break;
                case R.id.trailer_list_ly_jjzuqiu:
                    enterInfor("http://caipiao.163.com/m/redirectWapUrl.html?redirectUrl=http%3A%2F%2Fzxwap.caipiao.163.com%2Fjingcai%23fr%3Dapp","精彩足球");
                    break;
                case R.id.trailer_list_ly_jjlanqiu:
                    enterInfor("http://caipiao.163.com/m/redirectWapUrl.html?redirectUrl=http%3A%2F%2Fzxwap.caipiao.163.com%2Flancai%23fr%3Dapp","精彩篮球");
                    break;
                default:
                    break;
            }
        }
    };

    private void enterInfor(String url ,String title) {
        TrailerInforListActivity.launchActivity(TrailerListActivity.this,url,title);
    }


}
