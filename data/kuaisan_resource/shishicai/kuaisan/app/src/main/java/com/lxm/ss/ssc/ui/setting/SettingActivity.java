package com.lxm.ss.ssc.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lxm.ss.ssc.R;
import com.lxm.ss.ssc.Utils.PreferenceUtils;
import com.lxm.ss.ssc.Utils.StorageUtils;
import com.lxm.ss.ssc.Utils.ToastUtils;
import com.lxm.ss.ssc.base.BaseActivity;

public class SettingActivity extends BaseActivity {


    private Switch sw_03 ;
    private Switch sw_04 ;


    public static void launchActivity(Context context){
        Intent intent = new Intent(context,SettingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        initView();

        iniData();
    }

    private void initView() {

        sw_03 = (Switch) findViewById(R.id.setting_03_sw);
        sw_04 = (Switch) findViewById(R.id.setting_04_sw);

        sw_03.setOnCheckedChangeListener(onCheckedChangeListener);
        sw_04.setOnCheckedChangeListener(onCheckedChangeListener);
        findViewById(R.id.setting_01).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_02).setOnClickListener(mOnClickListener);

    }

    private void iniData() {

      boolean isSw_03 =   PreferenceUtils.getInstance(SettingActivity.this).getBooleanValue("03_sw",false);
      boolean isSw_04 =   PreferenceUtils.getInstance(SettingActivity.this).getBooleanValue("04_sw",false);


        sw_03.setChecked(isSw_03);
        sw_04.setChecked(isSw_04);
    }


    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {

                case R.id.setting_03_sw:
                    PreferenceUtils.getInstance(SettingActivity.this).setBooleanValue("03_sw",isChecked);

                    ToastUtils.show(SettingActivity.this,"设置成功");
                    break;

                case R.id.setting_04_sw:
                    PreferenceUtils.getInstance(SettingActivity.this).setBooleanValue("04_sw",isChecked);
                    ToastUtils.show(SettingActivity.this,"设置成功");
                    break;

                default:
                    break;
            }
        }
    };



    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.setting_02:
                    break;
                case R.id.setting_01:
                    StorageUtils.clearAllCache(SettingActivity.this);
                    ToastUtils.show(SettingActivity.this,"清理成功");
                    break;
                default:
                    break;
            }
        }
    };
}
