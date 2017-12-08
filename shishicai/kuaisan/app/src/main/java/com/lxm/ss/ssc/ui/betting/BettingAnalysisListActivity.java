package com.lxm.ss.ssc.ui.betting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.ssc.R;
import com.lxm.ss.ssc.Utils.ToastUtils;
import com.lxm.ss.ssc.adapter.NewLotterAdapter;
import com.lxm.ss.ssc.base.BaseActivity;
import com.lxm.ss.ssc.http.MyOkHttp;
import com.lxm.ss.ssc.http.OkHttpRequestListener;
import com.lxm.ss.ssc.model.LotterInfor;
import com.lxm.ss.ssc.model.RequestWYData;
import com.lxm.ss.ssc.parse.HtmlParse;
import com.lxm.ss.ssc.parse.model.ScreenReg;
import com.lxm.ss.ssc.ui.betting.model.BettingAnalysisInfor;
import com.lxm.ss.ssc.ui.lottery_infor.NewLotteryActivity;
import com.lxm.ss.ssc.ui.main.DetailParseWebContentActivity;
import com.lxm.ss.ssc.ui.trailer_infor.pre.TrailerInfor;
import com.lxm.ss.ssc.ui.trailer_infor.pre.TrailerInforListActivity;
import com.lxm.ss.ssc.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

public class BettingAnalysisListActivity extends BaseActivity {

    private String mUrl = "http://bbs.360.cn/forum.php?mod=forumdisplay&fid=246&filter=typeid&typeid=546" ;


    private ListView mListView ;
    private CustomTitleLinearlayout mCtlTitle ;

    private List<BettingAnalysisInfor> bettingAnalysisInfors ;
    private BettingAnalysisAdapter bettingAnalysisAdapter ;


    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, BettingAnalysisListActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betting_analysis_list);
        initView();

        initData();
    }


    private void initView() {

        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mListView = (ListView) findViewById(R.id.betting_analysis_list_lv);

        bettingAnalysisInfors = new ArrayList<>();
        bettingAnalysisAdapter = new BettingAnalysisAdapter(BettingAnalysisListActivity.this, -1 ,bettingAnalysisInfors);
        mListView.setAdapter(bettingAnalysisAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BettingAnalysisInfor bettingAnalysisInfor = bettingAnalysisInfors.get(position);
                String url = bettingAnalysisInfor.getUrl();
                String title = bettingAnalysisInfor.getTitle();


                String reg1 = "\\s*|\t|\r|\n";
                String regMatch3 = "<[^>]*>";

                List<ScreenReg> screenRegList = new ArrayList<>() ;

                ScreenReg screenReg  = new ScreenReg();
                screenReg.setRegStr(reg1);
                screenReg.setReplace("");
                screenReg.setScreen(true);
                screenRegList.add(screenReg);
                screenReg  = new ScreenReg();
                screenReg.setRegStr(regMatch3);
                screenReg.setReplace("\n");
                screenReg.setScreen(true);
                screenRegList.add(screenReg);



                DetailParseWebContentActivity.launchActivity(BettingAnalysisListActivity.this,url,title,screenRegList);
            }
        });


    }

    private void initData() {
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
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
                    ToastUtils.show(BettingAnalysisListActivity.this,"数据获取失败，请重试");
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                ToastUtils.show(BettingAnalysisListActivity.this,"数据获取失败，请重试");
            }
        });
    }

    private void parseHtmlString(String str) {

        List<BettingAnalysisInfor> bettingAnalysisInforList = HtmlParse.parseBettingInfor(str);

        this.bettingAnalysisInfors.addAll(bettingAnalysisInforList);
        bettingAnalysisAdapter.notifyDataSetChanged();

    }
}
