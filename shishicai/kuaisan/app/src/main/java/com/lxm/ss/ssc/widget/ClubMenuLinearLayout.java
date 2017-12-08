package com.lxm.ss.ssc.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxm.ss.ssc.R;
import com.lxm.ss.ssc.Utils.Zlog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.fromfactory.baselibrary.view.BadgeTextView;

/**
 * Created by lxm on 2016/11/29.
 */

public class ClubMenuLinearLayout extends LinearLayout {


    public static final int MENU_TYPE_ONE = 1000;
    public static final int MENU_TYPE_TWO = 1001;
    public static final int MENU_TYPE_THREE = 1002;

    @BindView(R.id.menu_one_img)
    ImageView menuOneImg;
    @BindView(R.id.menu_one_container)
    LinearLayout menuOneContainer;
    @BindView(R.id.menu_one_txt)
    TextView menuOneTxt;
    @BindView(R.id.menu_one)
    LinearLayout menuOne;
    @BindView(R.id.menu_two_img)
    ImageView menuTwoImg;
    @BindView(R.id.menu_two_container)
    LinearLayout menuTwoContainer;
    @BindView(R.id.menu_two_txt)
    TextView menuTwoTxt;
    @BindView(R.id.menu_two)
    LinearLayout menuTwo;
    @BindView(R.id.menu_three_img)
    ImageView menuThreeImg;
    @BindView(R.id.menu_three_container)
    LinearLayout menuThreeContainer;
    @BindView(R.id.menu_three_txt)
    TextView menuThreeTxt;
    @BindView(R.id.menu_three)
    LinearLayout menuThree;


    private BadgeTextView cartBadge;
    private BadgeTextView feedbackBadge;

    private Context mContext;

    public ClubMenuLinearLayout(Context context) {
        this(context, null);
//        initView(context);
    }

    public ClubMenuLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
//        initView(context);
    }

    public ClubMenuLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context);
    }

    //    public ClubMenuLinealayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    private void initView(Context context) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.main_menu, this);

        ButterKnife.bind(this);
//        cartBadge = new BadgeTextView(mContext, cartContainer);
//        cartBadge.setTextSize(9);
//        cartBadge.setBadgePosition(BadgeTextView.POSITION_TOP_RIGHT);
//
//
//        feedbackBadge = new BadgeTextView(mContext, feedbackContainer);
//        feedbackBadge.setTextSize(9);
//        feedbackBadge.setText("");
//        feedbackBadge.setBadgePosition(BadgeTextView.POSITION_TOP_RIGHT);
    }

    private ClubMenuListener mClubMenuListener;


    public void setListener(ClubMenuListener mClubMenuListener) {
        this.mClubMenuListener = mClubMenuListener;
    }

    @OnClick({R.id.menu_one, R.id.menu_two, R.id.menu_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu_one:
                if (mClubMenuListener !=null) mClubMenuListener.clickMenu0ne();
                break;
            case R.id.menu_two:
                if (mClubMenuListener !=null) mClubMenuListener.clickMenuTwo();
                break;
            case R.id.menu_three:
                if (mClubMenuListener !=null) mClubMenuListener.clickMenuThree();
                break;
        }
    }


    public void setSelectMenu(int type) {
        Zlog.ii("lxm ss setSelectMenu:" + type);
        menuOneTxt.setSelected(false);
        menuTwoTxt.setSelected(false);
        menuThreeTxt.setSelected(false);

        menuOneImg.setSelected(false);
        menuTwoImg.setSelected(false);
        menuThreeImg.setSelected(false);

        switch (type) {
            case MENU_TYPE_ONE: {
                if (menuOneTxt.isSelected()) {
                    return;
                }
                Zlog.ii("lxm ss setSelectMenu:" + type);
                menuOneTxt.setSelected(true);
                menuOneImg.setSelected(true);
            }
            break;
            case MENU_TYPE_TWO: {
                if (menuTwoTxt.isSelected()) {
                    return;
                }
                menuTwoTxt.setSelected(true);
                menuTwoImg.setSelected(true);
            }
            break;
            case MENU_TYPE_THREE: {
                if (menuThreeTxt.isSelected()) {
                    return;
                }
                menuThreeTxt.setSelected(true);
                menuThreeImg.setSelected(true);
            }
            break;
            default:
                break;
        }
    }

    public boolean isMenuSelected(int type) {

        boolean isSelect = false;
        switch (type) {
            case MENU_TYPE_ONE: {
                if (menuOneTxt.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            case MENU_TYPE_TWO: {
                if (menuTwoTxt.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            case MENU_TYPE_THREE: {
                if (menuThreeTxt.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            default:
                break;
        }
        return isSelect;
    }

    public void setCartCount(int num) {
        if (num > 0) {
            cartBadge.setCircleText(num);
        } else {
            cartBadge.setVisibility(View.INVISIBLE);
        }
    }

    public void setSupport(int num) {
        if (num > 0) {
            feedbackBadge.setCircleText(num);
        } else {
            feedbackBadge.setCircleText(0);
            feedbackBadge.setVisibility(View.INVISIBLE);
        }
    }

    public int getSupportNum() {

        String numStr = feedbackBadge.getText().toString();

        try {
            Zlog.ii("lxm getSupportNum:" + numStr);
            return Integer.valueOf(numStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();

            return 0;
        }
    }

    public interface ClubMenuListener {

        void clickMenu0ne();
        void clickMenuTwo();
        void clickMenuThree();

    }

}
