package com.lxm.ss.kuaisan.ui.trailer_infor.information;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.HtmlParse;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.ui.trailer_infor.pre.TrailerInfor;
import com.lxm.ss.kuaisan.ui.trailer_infor.pre.TrailerInforListActivity;
import com.lxm.ss.kuaisan.ui.trailer_infor.pre.TrailerInforListAdapter;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

public class InformationDetailListActivity extends BaseActivity {


    private CustomTitleLinearlayout mCtlTitle ;

    private ListView mListView ;

    private String mUrl ;
    private String mTitle ;

    private List<TrailerInfor> trailerInforList ;


    private InformationListAdapter mInformationListAdapter ;

    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context, String url, String title) {

        Intent intent = new Intent(context, InformationDetailListActivity.class);

        intent.putExtra(Constants.INTENT_URL,url);
        intent.putExtra(Constants.INTENT_TITLE,title);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail_list);

        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constants.INTENT_URL);
        mTitle = intent.getStringExtra(Constants.INTENT_TITLE);
        initView();

        initData();
    }



    private void initView() {

        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);

        mListView = (ListView) findViewById(R.id.informaiton_infor_list_lv);

        trailerInforList = new ArrayList<>();
        mInformationListAdapter = new InformationListAdapter(InformationDetailListActivity.this,-1,trailerInforList);

        mListView.setAdapter(mInformationListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TrailerInfor trailerInfor = trailerInforList.get(i);

                Zlog.ii("lxm onItemClick:" + trailerInfor );
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

        MyOkHttp.getInstance().getHtmlString(mUrl, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);
                if (o != null) {
                    String str   = (String) o;
                    parseHtmlString(str);

                }else {
                    ToastUtils.show(InformationDetailListActivity.this,"数据获取失败，请重试");
                }

            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                ToastUtils.show(InformationDetailListActivity.this,"数据获取失败，请重试");
            }
        });
    }

    private void parseHtmlString(String str) {

        List<TrailerInfor> trailerInforList = HtmlParse.parseTrailerInfor(str);

        this.trailerInforList.addAll(trailerInforList);
        mInformationListAdapter.notifyDataSetChanged();

    }
}
