package com.lxm.ss.kuaisan.ui.betting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.parse.HtmlParse;
import com.lxm.ss.kuaisan.parse.model.ScreenReg;
import com.lxm.ss.kuaisan.ui.betting.model.BettingAnalysisInfor;
import com.lxm.ss.kuaisan.ui.main.DetailParseWebContentActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

public class BettingTypeListActivity extends BaseActivity {

    private static final String URL_DEFAULT = "https://bbs.360.cn/forum.php?gid=239";

    private ListView mListView ;
    private CustomTitleLinearlayout mCtlTitle ;

    private List<BettingAnalysisInfor> bettingAnalysisInfors ;
    private BettingTypeAdapter bettingAnalysisAdapter ;

    private String mUrl = URL_DEFAULT ;

    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, BettingTypeListActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betting_type_list);
        Intent intent = getIntent();
        if (StringUtils.isNull(mUrl)) {
            mUrl = URL_DEFAULT ;
        }
        initView();
        initData();
    }


    private void initView() {

        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mListView = (ListView) findViewById(R.id.betting_type_list_lv);

        bettingAnalysisInfors = new ArrayList<>();
        bettingAnalysisAdapter = new BettingTypeAdapter(BettingTypeListActivity.this, -1 ,bettingAnalysisInfors);
        mListView.setAdapter(bettingAnalysisAdapter);

    }

    private void initData() {
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                finish();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BettingAnalysisInfor bettingAnalysisInfor = bettingAnalysisInfors.get(position);
                String url = bettingAnalysisInfor.getUrl();
                String title = bettingAnalysisInfor.getTitle();
                BettingAnalysisListActivity.launchActivity(BettingTypeListActivity.this,url,title);

            }
        });
        getData();
    }

    private void getData() {

        showBaseProgressDialog();
        MyOkHttp.getInstance().getHtml(mUrl, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);
                hideBaseProgressDialog();
                if (o != null) {
                    String str   = (String) o;
                    parseHtmlString(str);
                }else {
                    ToastUtils.show(BettingTypeListActivity.this,"数据获取失败，请重试");
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                hideBaseProgressDialog();
                ToastUtils.show(BettingTypeListActivity.this,"数据获取失败，请重试");
            }
        });
    }

    private void parseHtmlString(String str) {

        List<BettingAnalysisInfor> bettingAnalysisInforList = HtmlParse.parseBettingTypeList(str);

        this.bettingAnalysisInfors.addAll(bettingAnalysisInforList);
        bettingAnalysisAdapter.notifyDataSetChanged();

    }


}
