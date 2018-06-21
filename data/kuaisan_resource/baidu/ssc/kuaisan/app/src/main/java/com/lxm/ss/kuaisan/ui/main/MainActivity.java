package com.lxm.ss.kuaisan.ui.main;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lottery.ui.activity.KnowledgeActivity;
import com.lottery.ui.activity.lottery.NationWideActivity;
import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.base.BaseActivity;
import com.lxm.ss.kuaisan.base.BaseFragment;
import com.lxm.ss.kuaisan.ui.home.HomeFragment;
import com.lxm.ss.kuaisan.ui.home.HomeFragmentTwo;
import com.lxm.ss.kuaisan.ui.lottery_infor.LottertInforFragment;
import com.lxm.ss.kuaisan.ui.more.CommenProblemsActivity;
import com.lxm.ss.kuaisan.ui.more.CommentProblemsFragment;
import com.lxm.ss.kuaisan.widget.ClubMenuLinearLayout;

public class MainActivity extends BaseActivity {


    private ClubMenuLinearLayout mMenuLayout ;

//    private HomeFragment mHomeFragment ;
    private HomeFragmentTwo mHomeFragmentTwo ;
//    private StyleFragment mStyleFragment ;
//    private MoreFragment mMoreFragment ;

    private LottertInforFragment mLottertInforFragment ;
    private CommentProblemsFragment mCommentProblemsFragment ;

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        mMenuLayout = (ClubMenuLinearLayout) findViewById(R.id.main_tab_list);
        mMenuLayout.setSelectMenu(ClubMenuLinearLayout.MENU_TYPE_ONE);
    }

    private void initData() {
        mMenuLayout.setListener(new ClubMenuLinearLayout.ClubMenuListener() {

            @Override
            public void clickMenu0ne() {
                changeTab(ClubMenuLinearLayout.MENU_TYPE_ONE);
            }

            @Override
            public void clickMenuTwo() {
                changeTab(ClubMenuLinearLayout.MENU_TYPE_TWO);
            }

            @Override
            public void clickMenuThree() {
//                changeTab(ClubMenuLinearLayout.MENU_TYPE_THREE);
//                CommenProblemsActivity.launchActivity(MainActivity.this);

                Intent intent = new Intent(MainActivity.this, KnowledgeActivity.class);
                startActivity(intent);


            }

            @Override
            public void clickMenuFour() {
                Intent intent = new Intent(MainActivity.this, NationWideActivity.class);
                startActivity(intent);
//                changeTab(ClubMenuLinearLayout.MENU_TYPE_FOUR);
            }

            @Override
            public void clickMenuFive() {
                changeTab(ClubMenuLinearLayout.MENU_TYPE_FIVE);
            }
        });
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        mHomeFragment = new HomeFragment();
//        mCartFragment = new WebFragment();
//        mStyleFragment = new StyleFragment();
//        mMoreFragment = new MoreFragment();

        mHomeFragmentTwo = new HomeFragmentTwo();

        mLottertInforFragment = new LottertInforFragment();
        mCommentProblemsFragment = new CommentProblemsFragment();

        transaction.add(R.id.main_container, mLottertInforFragment);
        transaction.hide(mLottertInforFragment);
        transaction.add(R.id.main_container, mCommentProblemsFragment);
        transaction.hide(mCommentProblemsFragment);
        transaction.add(R.id.main_container, mHomeFragmentTwo);
        transaction.commitAllowingStateLoss();


        callBack();
    }

    private void changeTab(int tab) {
        if (mMenuLayout.isMenuSelected(tab)) {
            return;
        }
        mMenuLayout.setSelectMenu(tab);

        Bundle bundle = new Bundle();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (tab) {
            case ClubMenuLinearLayout.MENU_TYPE_ONE:
                hideFragment(transaction,mCommentProblemsFragment);
                hideFragment(transaction,mLottertInforFragment);
                showFragment(transaction,mHomeFragmentTwo,null);
                break;
            case ClubMenuLinearLayout.MENU_TYPE_FOUR:
                hideFragment(transaction,mHomeFragmentTwo);
                hideFragment(transaction,mCommentProblemsFragment);
                showFragment(transaction,mLottertInforFragment,null);

                break;
            case ClubMenuLinearLayout.MENU_TYPE_FIVE:

                hideFragment(transaction,mHomeFragmentTwo);
                hideFragment(transaction,mLottertInforFragment);
                showFragment(transaction,mCommentProblemsFragment,bundle);

                break;
        }

        transaction.commitAllowingStateLoss();
    }

    private void showFragment(FragmentTransaction transaction, BaseFragment baseFragment, Bundle bundle) {
        if (!baseFragment.isAdded()) {
            baseFragment.setArguments(bundle);
            transaction.add(R.id.main_container, baseFragment);
        } else {
            transaction.show(baseFragment);
        }
    }

    private void hideFragment(FragmentTransaction transaction,BaseFragment baseFragment) {
        if (baseFragment.isAdded()) {
            transaction.hide(baseFragment);
        }
    }


    private void callBack() {


    }
}
