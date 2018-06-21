package com.lxm.ss.kuaisan.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lottery.ui.activity.web.KnowledgeNextActivity;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.ui.more.CommenProblems;
import com.lxm.ss.kuaisan.ui.more.CommenProblemsActivity;
import com.lxm.ss.kuaisan.ui.more.CommentProblemsAdapter;
import com.lxm.ss.kuaisan.widget.CustomTitleLinearlayout;

import java.util.ArrayList;
import java.util.List;

public class HotKnowledgeActivity extends BaseActivity {

    private CustomTitleLinearlayout mCtlTitle ;

    private ListView mListView ;

    private List<CommenProblems> commenProblemsList ;

    private CommentProblemsAdapter mCommentProblemsAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_knowledge);

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
        mCommentProblemsAdapter = new CommentProblemsAdapter(HotKnowledgeActivity.this,R.layout.hot_knowledge_item  ,commenProblemsList);
        mListView.setAdapter(mCommentProblemsAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommenProblems commenProblems = commenProblemsList.get(position);
                String url = commenProblems.getUrl();
                Intent mIntent = new Intent(HotKnowledgeActivity.this, KnowledgeNextActivity.class);
                mIntent.putExtra("Knowledge_url", url);
                mIntent.putExtra("Knowledge_title", "详情");
                startActivity(mIntent);
            }
        });

        getData();

    }

    private void getData() {

        CommenProblems commenProblems = new CommenProblems();
        commenProblems.setProblems("双色球怎么玩");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/index.php?c=zhishi&a=ssq");
        commenProblemsList.add(commenProblems);

        commenProblems = new CommenProblems();
        commenProblems.setProblems("足彩混合过关详解");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-217.shtml");
        commenProblemsList.add(commenProblems);

        commenProblems = new CommenProblems();
        commenProblems.setProblems("竞彩篮球玩法");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-208.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("足彩看盘杀招技巧");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-203.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("比赛延期中断取消怎么算？");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-138.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("足彩013什么意思？ ");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-123.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("竞彩足球玩法图文详解 ");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-121.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("胜负彩点球怎么办?");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-119.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("买彩票合理利用概率学");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-104.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("返还率？");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-91.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("赔率术语大全");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-86.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("赔率术语大全");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-86.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("走地是什么意思");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-26.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("波胆什么意思？");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-17.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("什么是诱盘");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-16.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("竞彩足球怎么玩？");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-15.shtml");
        commenProblemsList.add(commenProblems);
        commenProblems = new CommenProblems();
        commenProblems.setProblems("什么是杀号？");
        commenProblems.setTitle(false);
        commenProblems.setUrl("http://m.500.com/info/zhishi/zs-11.shtml");
        commenProblemsList.add(commenProblems);

        //刷新
        mCommentProblemsAdapter.notifyDataSetChanged();
    }


}
