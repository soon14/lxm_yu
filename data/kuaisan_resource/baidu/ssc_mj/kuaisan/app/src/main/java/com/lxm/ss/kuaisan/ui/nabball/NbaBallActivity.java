package com.lxm.ss.kuaisan.ui.nabball;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.base.BaseActivity;

public class NbaBallActivity extends BaseActivity {

    /**
     * 启动详情页面
     *
     */
    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, NbaBallActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nba_ball);
        NbaBallHomeFragment nbaBallHomeFragment = new NbaBallHomeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.nba_ball_ly_all,nbaBallHomeFragment).commitAllowingStateLoss();

        nbaBallHomeFragment.setListener(new NbaBallHomeFragment.NbaBallListener() {
            @Override
            public void clickReturn() {
                finish();
            }
        });
    }
}
