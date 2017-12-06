package com.lxm.ss.shishicai.ui.main;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lxm.ss.shishicai.R;
import com.lxm.ss.shishicai.base.BaseActivity;
import com.lxm.ss.shishicai.base.BaseFragment;
import com.lxm.ss.shishicai.ui.home.HomeFragment;
import com.lxm.ss.shishicai.ui.more.MoreFragment;
import com.lxm.ss.shishicai.ui.style.StyleFragment;
import com.lxm.ss.shishicai.widget.ClubMenuLinearLayout;

public class MainActivity extends BaseActivity {


    private ClubMenuLinearLayout mMenuLayout ;

    private HomeFragment mHomeFragment ;
    private StyleFragment mStyleFragment ;
    private MoreFragment mMoreFragment ;

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
                changeTab(ClubMenuLinearLayout.MENU_TYPE_THREE);
            }
        });
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        mHomeFragment = new HomeFragment();
//        mCartFragment = new WebFragment();
        mStyleFragment = new StyleFragment();
        mMoreFragment = new MoreFragment();

        transaction.add(R.id.main_container, mMoreFragment);
        transaction.hide(mMoreFragment);
        transaction.add(R.id.main_container, mStyleFragment);
        transaction.hide(mStyleFragment);
        transaction.add(R.id.main_container, mHomeFragment);
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
                hideFragment(transaction,mMoreFragment);
                hideFragment(transaction,mStyleFragment);
                showFragment(transaction,mHomeFragment,null);
                break;
            case ClubMenuLinearLayout.MENU_TYPE_TWO:
                hideFragment(transaction,mHomeFragment);
                hideFragment(transaction,mMoreFragment);
                showFragment(transaction,mStyleFragment,null);

                break;
            case ClubMenuLinearLayout.MENU_TYPE_THREE:

                hideFragment(transaction,mHomeFragment);
                hideFragment(transaction,mStyleFragment);
                showFragment(transaction,mMoreFragment,bundle);

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
