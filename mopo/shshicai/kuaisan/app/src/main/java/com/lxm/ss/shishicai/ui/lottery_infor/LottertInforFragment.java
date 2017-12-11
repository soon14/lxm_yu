package com.lxm.ss.shishicai.ui.lottery_infor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.lxm.ss.shishicai.FFApplication;
import com.lxm.ss.shishicai.R;
import com.lxm.ss.shishicai.Utils.FastjsonUtil;
import com.lxm.ss.shishicai.Utils.ToastUtils;
import com.lxm.ss.shishicai.Utils.Zlog;
import com.lxm.ss.shishicai.adapter.NewLotterAdapter;
import com.lxm.ss.shishicai.base.BaseFragment;
import com.lxm.ss.shishicai.constant.Constants;
import com.lxm.ss.shishicai.http.MyOkHttp;
import com.lxm.ss.shishicai.http.OkHttpRequestListener;
import com.lxm.ss.shishicai.model.LotterInfor;
import com.lxm.ss.shishicai.model.RequestWYData;
import com.lxm.ss.shishicai.parse.model.ScreenReg;
import com.lxm.ss.shishicai.ui.main.DetailParseWebContentActivity;
import com.lxm.ss.shishicai.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * Created by lxm on 2017/12/11.
 */

public class LottertInforFragment extends BaseFragment {


    private ListView mListView ;
    private CustomTitleLinearlayout mCtlTitle ;


    private  List<LotterInfor> lotterInforList ;
    private NewLotterAdapter newLotterAdapter ;


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.fragment_lotter_infor, container, false);
        initView(vv);
        return vv;
    }


    private void initView(View vv) {
        mCtlTitle = (CustomTitleLinearlayout) vv.findViewById(R.id.ctl_title);
        mCtlTitle.setLeftTextVisible(false);
        mListView = (ListView) vv.findViewById(R.id.new_lottery_lv);
        
        lotterInforList = new ArrayList<>();
        newLotterAdapter = new NewLotterAdapter(getActivity(), -1 ,lotterInforList);
        mListView.setAdapter(newLotterAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LotterInfor lotterInfor = lotterInforList.get(position);
                ToastUtils.show(getActivity(),""+position);

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
                DetailParseWebContentActivity.launchActivity(getActivity(),url,gameEn,screenRegList);
            }
        });

    }


    private void initData() {
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
