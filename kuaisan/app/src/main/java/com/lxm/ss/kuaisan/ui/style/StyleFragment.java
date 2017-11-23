package com.lxm.ss.kuaisan.ui.style;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.ToastUtils;
import com.lxm.ss.kuaisan.base.BaseFragment;
import com.lxm.ss.kuaisan.ui.main.DetailWebViewActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

/**
 * Created by lxm on 2017/11/22.
 */

public class StyleFragment extends BaseFragment {

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
        View vv = inflater.inflate(R.layout.fragment_style, container, false);
        initView(vv);
        return vv;
    }

    private void initView(View vv) {
        CustomTitleLinearlayout  mCtlTitle = (CustomTitleLinearlayout) vv.findViewById(R.id.ctl_title);
        mCtlTitle.setRightVisible(false);
        mCtlTitle.setLeftTextVisible(false);

        vv.findViewById(R.id.ly_01).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_02).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_03).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_04).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_05).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_06).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_07).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_08).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_09).setOnClickListener(mOnClickListener);
        vv.findViewById(R.id.ly_10).setOnClickListener(mOnClickListener);

    }


    private void initData() {


    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.ly_01:
                    enterLocalWebView("http://caipiao.163.com/order/jczq-hunhe/#from=leftnav");
                    break;
                case R.id.ly_02:
                    enterLocalWebView("http://caipiao.163.com/order/ssq/#from=leftnav");
                    break;
                case R.id.ly_03:
                    enterLocalWebView("http://caipiao.163.com/order/dlt/#from=leftnav");
                    break;
                case R.id.ly_04:
                    enterLocalWebView("http://caipiao.163.com/order/jclq/mixp.html#from=leftnav");
                    break;
                case R.id.ly_05:
                    enterLocalWebView("http://caipiao.163.com/order/kl8/#from=leftnav");
                    break;
                case R.id.ly_06:
                    enterLocalWebView("http://caipiao.163.com/order/qlc/#from=leftnav");
                    break;
                case R.id.ly_07:
                    enterLocalWebView("http://caipiao.163.com/order/qxc/#from=leftnav");
                    break;
                case R.id.ly_08:
                    enterLocalWebView("http://caipiao.163.com/order/sfc/#from=leftnav");
                    break;
                case R.id.ly_09:
                    enterLocalWebView("http://caipiao.163.com/order/3d/#from=leftnav");
                    break;
                case R.id.ly_10:
                    enterLocalWebView("http://caipiao.163.com/order/pl3/#from=leftnav");
                    break;
                default:
                    break;
            }
        }
    };

    private void enterLocalWebView(String url) {

        DetailWebViewActivity.launchActivity(getActivity(),url);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
