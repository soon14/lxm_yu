package com.lxm.ss.kuaisan.ui.lottery_infor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.kuaisan.FFApplication;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.FastjsonUtil;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.adapter.NewLotterAdapter;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.constant.Constants;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.model.LotterInfor;
import com.lxm.ss.kuaisan.model.RequestWYData;
import com.lxm.ss.kuaisan.parse.model.ScreenReg;
import com.lxm.ss.kuaisan.ui.main.DetailContentActivity;
import com.lxm.ss.kuaisan.ui.main.DetailParseWebContentActivity;
import com.lxm.ss.kuaisan.ui.main.IntoActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

public class NewLotteryActivity extends BaseActivity {

    private ListView mListView ;
    private CustomTitleLinearlayout mCtlTitle ;


    private  List<LotterInfor> lotterInforList ;
    private NewLotterAdapter newLotterAdapter ;


    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, NewLotteryActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lottery);
        initView();
        initData();
    }

    private void initView() {

        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mListView = (ListView) findViewById(R.id.new_lottery_lv);

        lotterInforList = new ArrayList<>();
        newLotterAdapter = new NewLotterAdapter(NewLotteryActivity.this, -1 ,lotterInforList);
        mListView.setAdapter(newLotterAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LotterInfor lotterInfor = lotterInforList.get(position);
                ToastUtils.show(NewLotteryActivity.this,""+position);

                String url = "http://api.caipiao.163.com/queryAwardByCond.html?mobileType=android&ver=4.30&channel=qq_tab1&apiVer=1.1&apiLevel=27";

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

                String gameEn = lotterInfor.getGameEn();

                gameEn = StringUtils.isNotBlank(FFApplication.getInstance().mapLotterInfor.get(gameEn)) == true ?FFApplication.getInstance().mapLotterInfor.get(gameEn) :gameEn;
                url = url + "&count=20" + "&period=" + Long.valueOf(lotterInfor.getPeriodName()) +1
                        +"&gameEn=" +lotterInfor.getGameEn() ;
                DetailParseWebContentActivity.launchActivity(NewLotteryActivity.this,url,gameEn,screenRegList);
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

        getLotteryData();
    }

    private void getLotteryData() {

        showBaseProgressDialog();
        TypeReference typeReference = new TypeReference<RequestWYData>(){};
        MyOkHttp.getInstance().getLotterData(typeReference, new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);

                hideBaseProgressDialog();
                if (o != null) {
                    RequestWYData requestWYData = (RequestWYData) o;
                    lotterInforList.addAll(requestWYData.getData());
                    newLotterAdapter.notifyDataSetChanged();
                    Zlog.ii("lxm ");
                }else {
                    setDefaultData();
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
                hideBaseProgressDialog();
                setDefaultData();
            }
        });
    }

    private void setDefaultData() {
        TypeReference typeReference = new TypeReference<List<LotterInfor>>(){};

        List<LotterInfor> lotterInfors = (List<LotterInfor>) FastjsonUtil.getInstance().parseJson(Constants.LOTTER_INFOR, typeReference);

        lotterInforList.addAll(lotterInfors);
        newLotterAdapter.notifyDataSetChanged();

    }

}
