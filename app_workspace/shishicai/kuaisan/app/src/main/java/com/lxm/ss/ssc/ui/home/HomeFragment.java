package com.lxm.ss.ssc.ui.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxm.ss.ssc.R;
import com.lxm.ss.ssc.base.BaseFragment;
import com.lxm.ss.ssc.ui.betting.BettingAnalysisListActivity;
import com.lxm.ss.ssc.ui.lottery_infor.NewLotteryActivity;
import com.lxm.ss.ssc.ui.setting.SettingActivity;
import com.lxm.ss.ssc.ui.trailer_infor.information.InformationListActivity;
import com.lxm.ss.ssc.ui.trailer_infor.pre.TrailerListActivity;
import com.lxm.ss.ssc.widget.AutoRoll;
import com.lxm.ss.ssc.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.ScreenUtils;

/**
 * Created by lxm on 2017/11/22.
 */

public class HomeFragment extends BaseFragment {


    private CustomTitleLinearlayout mCtlTitle ;
    private View propagandaLink ;

    private AutoRoll arl_arl ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vv = inflater.inflate(R.layout.fragment_home, container, false);
        initView(vv);
        return vv;
    }

    private void initView(View vv) {
        mCtlTitle = (CustomTitleLinearlayout) vv.findViewById(R.id.ctl_title);
        // 轮播图
        propagandaLink = vv.findViewById(R.id.fl_wjsqpropaganda_link);
        arl_arl = (AutoRoll) vv.findViewById(R.id.fragment_auto_roll_pic);
        setImageParams(arl_arl);

        vv.findViewById(R.id.ly01).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly02).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly03).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly04).setOnClickListener(mOnClickListener);

        vv.findViewById(R.id.fragment_home_ly_style_01).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.fragment_home_ly_style_02).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.fragment_home_ly_style_03).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.fragment_home_ly_style_04).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.fragment_home_ly_style_05).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.fragment_home_ly_style_06).setOnClickListener(mOnClickListener);
    }


    private void initData() {

        mCtlTitle.settingRight();
        mCtlTitle.setRightVisible(true);
        mCtlTitle.setLeftTextVisible(false);
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {

            @Override
            public void clickRight() {
                SettingActivity.launchActivity(getActivity());
            }
        });


        initializeViewPage();
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.ly01:
//                    DetailContentActivity.launchActivity(getActivity(),getResources().getString(R.string.kuaosan_rules));
                    enterIntoActivity("http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE.html");
//                    IntoActivity.launchActivity(getActivity(),"http://caipiao.163.com/help/12/1108/15/8FQ3IUNH00754IHE.html");
                    break;
                case R.id.ly02:
                    TrailerListActivity.launchActivity(getActivity());
                    break;
                case R.id.ly03:

                    BettingAnalysisListActivity.launchActivity(getActivity());
//                    NewLotteryActivity.launchActivity(getActivity());
                    break;
                case R.id.ly04:
                    InformationListActivity.launchActivity(getActivity());
//                    ToastUtils.show(getActivity(),"敬请期待");
                    break;
                case R.id.fragment_auto_roll_pic:
                    int position = arl_arl.getCurrentIndex();

                    if (position ==  0) {
                        enterLocalWebView("http://m3.rrzcp8.com/activity/group/viewPage.html?activityId=tgnbx","","http://img.rrzcp8.cn/rrzcp/product/images/duobao/activity/1510900370200_1.jpg");
                    }else if (position ==1 ){
                        enterLocalWebView("http://fa.163.com/optg/activity/model/hhtDrawNewActivity/page/index?from=tgncpapppc","","https://pimg1.126.net/silver/product/fams/banner/819f29ca-d4fa-4859-a4f8-9b29fae9be42.jpg");
                    }else if (position == 2){
                        enterLocalWebView("http://m3.rrzcp8.com/activity/group/viewPage.html?activityId=tgnbx","","http://img.rrzcp8.cn/rrzcp/product/images/duobao/activity/1510900370200_1.jpg");
                    }

                    break;

                case R.id.fragment_home_ly_style_01:
                    enterIntoActivity("http://caipiao.163.com/help/14/0818/11/A3U6E00P00754IHE.html");
                    break;
                case R.id.fragment_home_ly_style_02:
                    enterIntoActivity("http://caipiao.163.com/help/12/1123/15/8H0M5BDL00754IHE.html");
                    break;
                case R.id.fragment_home_ly_style_03:
                    enterIntoActivity("http://caipiao.163.com/help/15/0104/10/AF41FV3O00754IHE.html");
                    break;
                case R.id.fragment_home_ly_style_04:
                    enterIntoActivity("http://caipiao.163.com/help/13/0625/18/92818L4F00754IHE.html");
                    break;
                case R.id.fragment_home_ly_style_05:
                    enterIntoActivity("http://caipiao.163.com/help/15/0202/15/AHF5PUCI00754IHE.html");
                    break;
                case R.id.fragment_home_ly_style_06:
                    break;

                default:
                    break;
            }
        }
    };


    // 轮播图额初始化
    private void initializeViewPage() {

        List<Drawable> imgUrls = new ArrayList<>();

        imgUrls.add(getResources().getDrawable(R.mipmap.ads_1));
        imgUrls.add(getResources().getDrawable(R.mipmap.ads_2));
        imgUrls.add(getResources().getDrawable(R.mipmap.ads_3));
        arl_arl.initData(imgUrls);
        arl_arl.setOnClickListener(mOnClickListener);
    }


    // 轮播图片 1242*525
    private void setImageParams(View view) {

        int screenWidth = ScreenUtils.getScreenWidth(getActivity());
        int width = screenWidth;
        int height = screenWidth * 525 / 1242;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
