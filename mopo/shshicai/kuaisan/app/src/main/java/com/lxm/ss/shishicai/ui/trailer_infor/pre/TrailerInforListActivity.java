package com.lxm.ss.shishicai.ui.trailer_infor.pre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lxm.ss.shishicai.R;
import com.lxm.ss.shishicai.Utils.ToastUtils;
import com.lxm.ss.shishicai.Utils.Zlog;
import com.lxm.ss.shishicai.base.BaseActivity;
import com.lxm.ss.shishicai.constant.Constants;
import com.lxm.ss.shishicai.http.MyOkHttp;
import com.lxm.ss.shishicai.http.OkHttpRequestListener;
import com.lxm.ss.shishicai.parse.HtmlParse;
import com.lxm.ss.shishicai.parse.model.ScreenReg;
import com.lxm.ss.shishicai.ui.main.DetailParseWebContentActivity;
import com.lxm.ss.shishicai.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

public class TrailerInforListActivity extends BaseActivity {

    private CustomTitleLinearlayout mCtlTitle ;

    private ListView mListView ;

    private String mUrl ;
    private String mTitle ;

    private List<TrailerInfor> trailerInforList ;

    private TrailerInforListAdapter mTrailerInforListAdapter ;
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context,String url,String title) {
        Intent intent = new Intent(context, TrailerInforListActivity.class);

        intent.putExtra(Constants.INTENT_URL,url);
        intent.putExtra(Constants.INTENT_TITLE,title);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_infor_list);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constants.INTENT_URL);
        mTitle = intent.getStringExtra(Constants.INTENT_TITLE);

        initView();
        initData();
    }

    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);

        mListView = (ListView) findViewById(R.id.trailer_infor_list_lv);

        trailerInforList = new ArrayList<>();
        mTrailerInforListAdapter = new TrailerInforListAdapter(TrailerInforListActivity.this,-1,trailerInforList);

        mListView.setAdapter(mTrailerInforListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TrailerInfor trailerInfor = trailerInforList.get(i);

                String urlLink = trailerInfor.getUrlLink();

                Zlog.ii("lxm onItemClick:" + trailerInfor );

                String reg1 = "\\s*|\t|\r|\n";
                String regMatch2 = "<title>(.*?)</title>|<divclass=\"articleCon\">(.*?)</p><divclass=\"ep-sourcecDGray\">";

                String regMatch3 = "<style>(.*?)</style>";
                String regMatch4 = "<[^>]*>";
                String regMatch5 = "\n\n";

                List<ScreenReg> regList = new ArrayList<>() ;
                ScreenReg screenReg  = new ScreenReg();
                screenReg.setRegStr(reg1);
                screenReg.setReplace("");
                screenReg.setScreen(true);
                regList.add(screenReg);
                screenReg  = new ScreenReg();
                screenReg.setRegStr(regMatch2);
                screenReg.setReplace("");
                screenReg.setScreen(false);
                regList.add(screenReg);
                screenReg  = new ScreenReg();
                screenReg.setRegStr(regMatch3);
                screenReg.setReplace("");
                screenReg.setScreen(true);
                regList.add(screenReg);
                screenReg  = new ScreenReg();
                screenReg.setRegStr(regMatch4);
                screenReg.setReplace("\n");
                screenReg.setScreen(true);
                regList.add(screenReg);
                screenReg  = new ScreenReg();
                screenReg.setRegStr(regMatch5);
                screenReg.setReplace("\n");
                screenReg.setScreen(true);
                regList.add(screenReg);
                DetailParseWebContentActivity.launchActivity(TrailerInforListActivity.this,urlLink,"",regList);


            }
        });

    }

    private void initData() {
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                super.clickLeft();
                finish();
            }
        });

        getData();
    }

    private void getData() {

        MyOkHttp.getInstance().getHtml(mUrl, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);
                if (o != null) {
                    String str   = (String) o;
                    parseHtmlString(str);

                }else {
                    ToastUtils.show(TrailerInforListActivity.this,"数据获取失败，请重试");
                }

            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                ToastUtils.show(TrailerInforListActivity.this,"数据获取失败，请重试");
            }
        });
    }

    private void parseHtmlString(String str) {

        List<TrailerInfor> trailerInforList = HtmlParse.parseTrailerInfor(str);

        this.trailerInforList.addAll(trailerInforList);
        mTrailerInforListAdapter.notifyDataSetChanged();

    }
}
