package com.lxm.ss.kuaisan.ui.more;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.base.BaseFragment;
import com.lxm.ss.kuaisan.ui.main.DetailWebViewActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import org.w3c.dom.Text;

/**
 * Created by lxm on 2017/11/22.
 */

public class MoreFragment extends BaseFragment {


    private CustomTitleLinearlayout mCtlTitle ;

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
        View vv = inflater.inflate(R.layout.fragment_more, container, false);
        initView(vv);
        return vv;
    }

    private void initView(View vv) {
        mCtlTitle = (CustomTitleLinearlayout) vv.findViewById(R.id.ctl_title);
        mCtlTitle.setRightVisible(false);
        mCtlTitle.setLeftTextVisible(false);

        vv.findViewById(R.id.fragment_more_img).setOnClickListener(mOnClickListener);
        TextView link01 = (TextView) vv.findViewById(R.id.fragment_more_link01);
        link01.setOnClickListener(mOnClickListener);
        TextView link02 = (TextView) vv.findViewById(R.id.fragment_more_link02);
        link02.setOnClickListener(mOnClickListener);
        TextView link03 = (TextView) vv.findViewById(R.id.fragment_more_link03);
        link03.setOnClickListener(mOnClickListener);
        TextView link04 = (TextView) vv.findViewById(R.id.fragment_more_link04);
        link04.setOnClickListener(mOnClickListener);
        TextView link05 = (TextView) vv.findViewById(R.id.fragment_more_link05);
        link05.setOnClickListener(mOnClickListener);

        addTextLine(link01);
        addTextLine(link02);
        addTextLine(link03);
        addTextLine(link04);
        addTextLine(link05);


    }


    private void initData() {

        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {

            @Override
            public void clickRight() {

            }
        });
    }

    private void addTextLine(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        textView.getPaint().setAntiAlias(true);//抗锯齿
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.fragment_more_img:
                    enterLocalWebView("http://caipiao.163.com/help/");
                    break;
                case R.id.fragment_more_link01:
                    enterLocalWebView("http://caipiao.163.com/help/14/0627/10/9VO6LSTN00754KN4.html#from=relevant");
                    break;
                case R.id.fragment_more_link02:
                    enterLocalWebView("http://caipiao.163.com/help/14/0626/19/9VMJ9SNH00754KN4.html#from=relevant");
                    break;
                case R.id.fragment_more_link03:
                    enterLocalWebView("http://caipiao.163.com/help/14/0627/15/9VOO6TKI00754KN4.html#from=relevant");
                    break;
                case R.id.fragment_more_link04:
                    enterLocalWebView("http://caipiao.163.com/help/14/0627/15/9VOORTAJ00754KN4.html#from=relevant");
                    break;
                case R.id.fragment_more_link05:
                    enterLocalWebView("http://caipiao.163.com/help/14/0627/15/9VOOEAG100754KN4.html#from=relevant");
                    break;



                default:
                    break;
            }
        }
    } ;


    private void enterLocalWebView(String url) {

        DetailWebViewActivity.launchActivity(getActivity(),url);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
