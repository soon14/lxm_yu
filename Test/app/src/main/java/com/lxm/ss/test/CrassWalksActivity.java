package com.lxm.ss.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.xwalk.core.XWalkView;

public class CrassWalksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crass_walks);

        XWalkView mXWalkView = (XWalkView) findViewById(R.id.activity_main_s);
        mXWalkView.load("http://pre.fromfactory.club/theme/sport20170908?position=banner_1", null);
    }

}
