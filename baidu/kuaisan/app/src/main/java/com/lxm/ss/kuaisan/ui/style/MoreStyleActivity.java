package com.lxm.ss.shishicai.ui.style;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lxm.ss.shishicai.R;
import com.lxm.ss.shishicai.base.BaseActivity;
import com.lxm.ss.shishicai.ui.main.IntoActivity;
import com.lxm.ss.shishicai.widget.CustomTitleLinearlayout;

public class MoreStyleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_style);
        initView();
    }


    private void initView() {
        CustomTitleLinearlayout mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);
        mCtlTitle.setRightVisible(false);
        mCtlTitle.setLeftTextVisible(false);

        findViewById(R.id.ly_01).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_02).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_03).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_04).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_05).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_06).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_07).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_08).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_09).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_10).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_11).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_12).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_13).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_14).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_15).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_16).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_17).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_18).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_19).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_20).setOnClickListener(mOnClickListener);

        findViewById(R.id.ly_ks01).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_ks02).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_ks03).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_ks04).setOnClickListener(mOnClickListener);
        findViewById(R.id.ly_ks05).setOnClickListener(mOnClickListener);

    }


    private void initData() {


    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){


                case R.id.ly_ks01:
                    enterIntoActivity("http://caipiao.163.com/help/14/0818/11/A3U6E00P00754IHE.html");
                    break;
                case R.id.ly_ks02:
                    enterIntoActivity("http://caipiao.163.com/help/12/1123/15/8H0M5BDL00754IHE.html");
                    break;
                case R.id.ly_ks03:
                    enterIntoActivity("http://caipiao.163.com/help/15/0104/10/AF41FV3O00754IHE.html");
                    break;
                case R.id.ly_ks04:
                    enterIntoActivity("http://caipiao.163.com/help/13/0625/18/92818L4F00754IHE.html");
                    break;
                case R.id.ly_ks05:
                    enterIntoActivity("http://caipiao.163.com/help/15/0202/15/AHF5PUCI00754IHE.html");
                    break;

                case R.id.ly_01:

                    enterIntoActivity("http://caipiao.163.com/help/10/0726/10/6CGUOF0200754IHF.html");
//                    enterLocalWebView("http://caipiao.163.com/order/jczq-hunhe/#from=leftnav","",R.mipmap.aa_01);
                    break;
                case R.id.ly_02:
                    enterIntoActivity("http://caipiao.163.com/help/10/0726/11/6CH18N6300754IHG.html");
//                    enterLocalWebView("http://caipiao.163.com/order/ssq/#from=leftnav",getResources().getString(R.string.content_03));
                    break;
                case R.id.ly_03:
                    enterIntoActivity("http://caipiao.163.com/help/10/0726/11/6CH2ICTV00754IHH.html");
//                    enterLocalWebView("http://caipiao.163.com/order/dlt/#from=leftnav",getResources().getString(R.string.content_04),R.mipmap.aa_01);
                    break;
                case R.id.ly_04:
                    enterIntoActivity("http://caipiao.163.com/help/10/0723/15/6C9OM7UM00754IHI.html");
//                    enterLocalWebView("http://caipiao.163.com/order/jclq/mixp.html#from=leftnav",getResources().getString(R.string.content_05));
                    break;
                case R.id.ly_05:
                    enterIntoActivity("http://caipiao.163.com/help/10/0726/10/6CGS2JDN00754IHJ.html");
//                    enterLocalWebView("http://caipiao.163.com/order/kl8/#from=leftnav",getResources().getString(R.string.content_06),R.mipmap.aa_02);
                    break;
                case R.id.ly_06:
                    enterIntoActivity("http://caipiao.163.com/help/10/0723/11/6C9BEBRJ00754IHK.html");
//                    enterLocalWebView("http://caipiao.163.com/order/qlc/#from=leftnav",getResources().getString(R.string.content_07),R.mipmap.aa_03);
                    break;
                case R.id.ly_07:
                    enterIntoActivity("http://caipiao.163.com/help/10/0723/15/6C9NR01E00754IHL.html");
//                    enterLocalWebView("http://caipiao.163.com/order/qxc/#from=leftnav",getResources().getString(R.string.content_08));
                    break;
                case R.id.ly_08:
                    enterIntoActivity("http://caipiao.163.com/help/10/0804/10/6D84U99T00754IIB.html");
//                    enterLocalWebView("http://caipiao.163.com/order/sfc/#from=leftnav",getResources().getString(R.string.content_09));
                    break;
                case R.id.ly_09:
                    enterIntoActivity("http://caipiao.163.com/help/13/0312/13/8PP5THH100754IHE.html");
//                    enterLocalWebView("http://caipiao.163.com/order/3d/#from=leftnav",getResources().getString(R.string.content_10));
                    break;
                case R.id.ly_10:
                    enterIntoActivity("http://caipiao.163.com/help/13/0507/13/8U9CNPTP00754IHE.html");
//                    enterLocalWebView("http://caipiao.163.com/order/pl3/#from=leftnav",getResources().getString(R.string.content_11));
                    break;
                case R.id.ly_11:
                    enterIntoActivity("http://caipiao.163.com/help/13/0925/14/99KI1OE100754IHE.html");
                    break;
                case R.id.ly_12:
                    enterIntoActivity("http://caipiao.163.com/help/14/0805/21/A2TOI4UF00754IHE.html");
                    break;
                case R.id.ly_13:
                    enterIntoActivity("http://caipiao.163.com/help/14/0805/20/A2TO8PR100754IHE.html");
                    break;
                case R.id.ly_14:
                    enterIntoActivity("http://caipiao.163.com/help/14/0922/15/A6OO9M8A00754IHE.html");
                    break;
                case R.id.ly_15:
                    enterIntoActivity("http://caipiao.163.com/help/10/0811/14/6DQGMQC400754IIO.html");
                    break;
                case R.id.ly_16:
                    enterIntoActivity("http://caipiao.163.com/help/11/0419/10/720DMA4H00754IHE.html");
                    break;
                case R.id.ly_17:
                    enterIntoActivity("http://caipiao.163.com/help/10/0916/09/6GMO4MVK00754IL8.html");
                    break;
                case R.id.ly_18:
                    enterIntoActivity("http://caipiao.163.com/help/11/0802/16/7AFD396I00754JGL.html");
                    break;
                case R.id.ly_19:
                    enterIntoActivity("http://caipiao.163.com/help/11/0802/16/7AFD4SJ800754JGL.html");
                    break;
                case R.id.ly_20:
                    enterIntoActivity("http://caipiao.163.com/help/10/1102/14/6KG9T1RR00754IO9.html");
                    break;
                default:
                    break;
            }
        }
    };

    public void enterIntoActivity(String url ) {
        IntoActivity.launchActivity(MoreStyleActivity.this,url);
    }
}
