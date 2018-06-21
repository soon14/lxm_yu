package com.lxm.ss.kuaisan.ui.more;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.parse.model.ScreenReg;
import com.lxm.ss.kuaisan.ui.main.DetailParseWebContentActivity;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

public class CommenProblemsActivity extends BaseActivity {

    private CustomTitleLinearlayout mCtlTitle ;

    private ListView mListView ;

    private List<CommenProblems> commenProblemsList ;

    private CommentProblemsAdapter mCommentProblemsAdapter ;


    /**
     *
     */
    public static void launchActivity(Context context) {
        Intent intent = new Intent(context, CommenProblemsActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commen_problems);
        initView();
        initData();
    }

    private void initView() {
        mCtlTitle = (CustomTitleLinearlayout) findViewById(R.id.ctl_title);

        mListView = (ListView) findViewById(R.id.commen_problems_lv);


    }

    private void initData() {
        mCtlTitle.setListener(new CustomTitleLinearlayout.CustomTitleListener() {
            @Override
            public void clickLeft() {
                finish();
            }
        });
        commenProblemsList = new ArrayList<>();
        mCommentProblemsAdapter = new CommentProblemsAdapter(CommenProblemsActivity.this,R.layout.comment_problems_item ,commenProblemsList);
        mListView.setAdapter(mCommentProblemsAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommenProblems commenProblems = commenProblemsList.get(position);
                if (!commenProblems.isTitle()) {
                    String reg1 = "\\s*|\t|\r|\n";
                    String regMatch2 = "<title>(.*?)</title>|<divclass=\"help_t_int\">(.*?)</div></div><divclass=\"clear\"></div>";

                    String regMatch3 = "<title>(.*?)</title>|<p>(.*?)</p>";
                    String regMatch4 = "<[^>]*>";
                    String regMatch5 = "\n\n";

                    List<ScreenReg> regList = new ArrayList<>() ;
                    ScreenReg screenReg  = new ScreenReg();
                    screenReg.setRegStr(reg1);
                    screenReg.setReplace("");
                    screenReg.setScreen(true);
                    regList.add(screenReg);
                    screenReg  = new ScreenReg();
                    screenReg.setRegStr(regMatch2);
                    screenReg.setReplace("");
                    screenReg.setScreen(false);
                    regList.add(screenReg);
                    screenReg  = new ScreenReg();
                    screenReg.setRegStr(regMatch3);
                    screenReg.setReplace("");
                    screenReg.setScreen(false);
                    regList.add(screenReg);
                    screenReg  = new ScreenReg();
                    screenReg.setRegStr(regMatch4);
                    screenReg.setReplace("\n");
                    screenReg.setScreen(true);
                    regList.add(screenReg);
                    screenReg  = new ScreenReg();
                    screenReg.setRegStr(regMatch5);
                    screenReg.setReplace("\n");
                    screenReg.setScreen(true);
                    regList.add(screenReg);
                    DetailParseWebContentActivity.launchActivity(CommenProblemsActivity.this,commenProblems.getUrl(),"",regList);
                }
            }
        });

        getData();
    }


    private void getData() {


        CommenProblems commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_00));
        commenProblems.setTitle(true);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_01));
        commenProblems.setUrl("http://caipiao.163.com/help/10/1013/18/6IT6J73700754II6.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_02));
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/13/6CMC5P5900754II6.html");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_03));
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://caipiao.163.com/help/10/0919/12/6GUMSGF000754II6.html");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_04));
        commenProblems.setUrl("http://caipiao.163.com/help/special/00754II5/caipiao_qa_guide.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_05));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/13/6CMC6PH400754II6.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_06));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMT56O900754II6.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_07));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMT5VQO00754II6.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);


        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_10));
        commenProblems.setTitle(true);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_11));
        commenProblems.setUrl("http://caipiao.163.com/help/11/0301/10/6U27HKLD00754II7.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_12));
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/13/6CMCAL5300754II7.html");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_13));
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://caipiao.163.com/help/10/1009/15/6IIHSBTA00754II7.html");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_14));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMTE65N00754II7.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_15));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/13/6CMCQ58O00754II7.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);

        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_20));
        commenProblems.setTitle(true);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_21));
        commenProblems.setUrl("http://caipiao.163.com/help/11/0721/16/79GGMQLR00754II8.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_22));
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMTHRD700754II8.html");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_23));
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMTIU4V00754II8.html");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_24));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMTGOIE00754II8.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_25));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMTFN1G00754II8.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);


        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_30));
        commenProblems.setTitle(true);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_31));
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMTK0GU00754II9.html");
        commenProblems.setTitle(false);
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems(getResources().getString(R.string.problems_32));
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://caipiao.163.com/help/10/0728/18/6CMTM28Q00754II9.html");
        commenProblemsList.add(commenProblems);

        //刷新
        mCommentProblemsAdapter.notifyDataSetChanged();
    }
}
