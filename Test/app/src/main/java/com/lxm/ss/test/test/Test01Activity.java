package com.lxm.ss.test.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lxm.ss.test.MyJni;
import com.lxm.ss.test.R;

import org.w3c.dom.Text;

public class Test01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test01);


        TextView textView = (TextView) findViewById(R.id.txt_test01);

        textView.setText("lxm :" + MyJni.sayHello());

    }


}
