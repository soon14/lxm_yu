package com.lxm.ss.kuaisan.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.base.BaseActivity;

public class SettingActivity extends BaseActivity {


    public static void launchActivity(Context context){
        Intent intent = new Intent(context,SettingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }
}
