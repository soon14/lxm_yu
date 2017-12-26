package com.lxm.ss.kuaisan.ui.lottery_infor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.model.LotterInfor;
import com.lxm.ss.kuaisan.parse.HtmlParse;
import com.lxm.ss.kuaisan.ui.main.DetailContentActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

public class LotteryInforDetailActivity extends BaseActivity {

    private ListView mListView ;
    private CustomTitleLinearlayout mCtlTitle ;

    private TextView txt_content ;

    private  List<LotterInfor> lotterInforList ;
    private NewLotterAdapter newLotterAdapter ;

    private String url;
    private String title ;
    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context,String title ,String url) {
        Intent intent = new Intent(context, LotteryInforDetailActivity.class);
        intent.putExtra(Constants.INTENT_TITLE,title);
        intent.putExtra(Constants.INTENT_URL,url);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_infor_detail);
        Intent intent = getIntent();
        url = intent.getStringExtra(Constants.INTENT_URL);
        title = intent.getStringExtra(Constants.INTENT_TITLE);
        initView();
        initData();
    }

    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mListView = (ListView) findViewById(R.id.new_lottery_lv);
        txt_content = (TextView) findViewById(R.id.txt_content);

        lotterInforList = new ArrayList<>();
        newLotterAdapter = new NewLotterAdapter(LotteryInforDetailActivity.this, R.layout.lotter_item_infor ,lotterInforList);
        mListView.setAdapter(newLotterAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LotterInfor lotterInfor = lotterInforList.get(position);

                String awardTime = lotterInfor.getAwardTime();
                String awardNo = lotterInfor.getAwardNo();
                String periodName = lotterInfor.getPeriodName();
                String totalPool = lotterInfor.getTotalPool();
                String totalSale = lotterInfor.getTotalSale();
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("第"+periodName +"期 " +awardTime +"\n开奖号码：" +awardNo +"\n") ;

                if (StringUtils.isNotBlank(totalSale)) {
                    stringBuilder.append("本期销售：") .append(totalSale);
                }
                if (StringUtils.isNotBlank(totalPool)) {
                    stringBuilder.append("\n总奖池：") .append(totalPool);
                }
                DetailContentActivity.launchActivity(LotteryInforDetailActivity.this,stringBuilder.toString(),lotterInfor.getGameEn());

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
        if (StringUtils.isNotBlank(title)) {
            mCtlTitle.setTitleCenter(title);
        }
        getLotteryData();
    }

    private void getLotteryData() {

        showBaseProgressDialog();
        MyOkHttp.getInstance().getHtml(url, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);
                hideBaseProgressDialog();
                if (o != null) {
                    String str = (String) o;
                    Zlog.ii("lxm getLotteryData:" + str);
                    parseData(str);
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                hideBaseProgressDialog();

            }
        });
    }


    private void parseData(String str) {
        List<LotterInfor> lotterInfors = HtmlParse.parseLotterInfor(str);
        lotterInforList.addAll(lotterInfors);
        newLotterAdapter.notifyDataSetChanged();

        if (lotterInfors.size() == 0) {
            txt_content.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            ToastUtils.show(LotteryInforDetailActivity.this,"暂时没有更新");
        }else {
            txt_content.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
