package com.lxm.ss.kuaisan.ui.betting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lxm.ss.kuaisan.ui.betting.BettingAnalysisAdapter;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.parse.HtmlParse;
import com.lxm.ss.kuaisan.parse.model.ScreenReg;
import com.lxm.ss.kuaisan.ui.betting.model.BettingAnalysisInfor;
import com.lxm.ss.kuaisan.ui.main.DetailParseWebContentActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

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

                String reg2 = "<divclass=\"t_fsz1\">(.*?)</div>" ;
                String regMatch3 = "<[^>]*>";
                String regMatch4 = "\n\n";

                List<ScreenReg> screenRegList = new ArrayList<>() ;

                ScreenReg screenReg  = new ScreenReg();
                screenReg.setRegStr(reg1);
                screenReg.setReplace("");
                screenReg.setScreen(true);
                screenRegList.add(screenReg);

                screenReg  = new ScreenReg();
                screenReg.setRegStr(reg2);
                screenReg.setReplace("");
                screenReg.setScreen(false);
                screenRegList.add(screenReg);

                screenReg  = new ScreenReg();
                screenReg.setRegStr(regMatch3);
                screenReg.setReplace("\n");
                screenReg.setScreen(true);
                screenRegList.add(screenReg);

                screenReg  = new ScreenReg();
                screenReg.setRegStr(regMatch4);
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
                    ToastUtils.show(BettingAnalysisListActivity.this,"数据获取失败，请重试");
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                hideBaseProgressDialog();
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
