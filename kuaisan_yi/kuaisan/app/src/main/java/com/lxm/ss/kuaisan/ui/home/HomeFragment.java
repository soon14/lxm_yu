package com.lxm.ss.kuaisan.ui.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.base.BaseFragment;
import com.lxm.ss.kuaisan.ui.main.DetailContentActivity;
import com.lxm.ss.kuaisan.ui.main.DetailWebViewActivity;
import com.lxm.ss.kuaisan.ui.main.SettingActivity;
import com.lxm.ss.kuaisan.ui.trailer_infor.TrailerListActivity;
import com.lxm.ss.kuaisan.widget.AutoRoll;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

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
    }


    private void initData() {

        mCtlTitle.settingRight();
        mCtlTitle.setRightVisible(true);
        mCtlTitle.setLeftTextVisible(false);
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {

            @Override
            public void clickRight() {
                ToastUtils.show(getActivity(),"敬请期待");
//                SettingActivity.launchActivity(getActivity());
            }
        });


        initializeViewPage();
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.ly01:
                    DetailContentActivity.launchActivity(getActivity(),getResources().getString(R.string.kuaosan_rules));
                    break;
                case R.id.ly02:
                    TrailerListActivity.launchActivity(getActivity());
//                    enterLocalWebView("http://zxwap.caipiao.163.com/news");
                    break;
                case R.id.ly03:
                    enterLocalWebView("http://caipiao.163.com/award/");

                    break;
                case R.id.ly04:

                    break;

                case R.id.fragment_auto_roll_pic:
                    int position = arl_arl.getCurrentIndex();

                    if (position ==  0) {
                        enterLocalWebView("http://caipiao.163.com/m/redirectWapUrl.html?redirectUrl=http%3A%2F%2Ffa.163.com%2Foptg%2Factivity%2Fmodel%2FhhtDrawNewActivity%2Fpage%2Findex%3Ffrom%3Dtgncpapptt%26ver%3D4.29%26udid%3D622d1e645acbd057");
                    }else if (position ==1 ){
                        enterLocalWebView("http://caipiao.163.com/m/redirectWapUrl.html?redirectUrl=http%3A%2F%2Ffa.163.com%2Foptg%2Factivity%2Fmodel%2FhhtDrawNewActivity%2Fpage%2Findex%3Ffrom%3Dtgncpapptt%26ver%3D4.29%26udid%3D622d1e645acbd057");
                    }else if (position == 2){
                        enterLocalWebView("https://caipiao.163.com/nfop/tgwdaoliu/index.htm?ver=4.29&udid=622d1e645acbd057");
                    }

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

    private void enterLocalWebView(String url) {
        DetailWebViewActivity.launchActivity(getActivity(),url);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
