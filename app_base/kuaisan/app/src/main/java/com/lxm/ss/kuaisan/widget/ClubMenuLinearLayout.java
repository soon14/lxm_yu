package com.lxm.ss.kuaisan.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.Zlog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import club.fromfactory.baselibrary.view.BadgeTextView;

/**
 * Created by lxm on 2016/11/29.
 */

public class ClubMenuLinearLayout extends LinearLayout {


    public static final int MENU_TYPE_HOME = 1000;
    public static final int MENU_TYPE_CART = 1001;
    public static final int MENU_TYPE_CATEGORY = 1002;
    public static final int MENU_TYPE_ACCOUNT = 1003;
    public static final int MENU_TYPE_SUPPORT = 1004;

    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.homeContainer)
    LinearLayout homeContainer;
    @BindView(R.id.txt_home)
    TextView txtHome;
    @BindView(R.id.home_larger)
    LinearLayout homeLarger;
    @BindView(R.id.categories_img)
    ImageView categoriesImg;
    @BindView(R.id.categoriesContainer)
    LinearLayout categoriesContainer;
    @BindView(R.id.categories_text)
    TextView categoriesText;
    @BindView(R.id.categories_larger)
    LinearLayout categoriesLarger;
    @BindView(R.id.cart_img)
    ImageView cartImg;
    @BindView(R.id.cartContainer)
    LinearLayout cartContainer;
    @BindView(R.id.cart_text)
    TextView cartText;
    @BindView(R.id.cart_larger)
    LinearLayout cartLarger;
    @BindView(R.id.account_img)
    ImageView accountImg;
    @BindView(R.id.accountContainer)
    LinearLayout accountContainer;
    @BindView(R.id.account_text)
    TextView accountText;
    @BindView(R.id.account_larger)
    LinearLayout accountLarger;
    @BindView(R.id.img_feedback)
    ImageView imgFeedback;
    @BindView(R.id.feedbackContainer)
    LinearLayout feedbackContainer;
    @BindView(R.id.feedback)
    LinearLayout feedback;
    @BindView(R.id.txt_support)
    TextView txtSupport;

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
        cartBadge = new BadgeTextView(mContext, cartContainer);
        cartBadge.setTextSize(9);
        cartBadge.setBadgePosition(BadgeTextView.POSITION_TOP_RIGHT);


        feedbackBadge = new BadgeTextView(mContext, feedbackContainer);
        feedbackBadge.setTextSize(9);
        feedbackBadge.setText("");
        feedbackBadge.setBadgePosition(BadgeTextView.POSITION_TOP_RIGHT);
        setMenuLanguage();
    }

    private ClubMenuListener mClubMenuListener ;

    @OnClick({R.id.home_larger, R.id.categories_larger, R.id.cart_larger, R.id.account_larger, R.id.feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_larger:
                if (mClubMenuListener != null) mClubMenuListener.clickHome();
                break;
            case R.id.categories_larger:
                if (mClubMenuListener != null) mClubMenuListener.clickCategories();
                break;
            case R.id.cart_larger:
                if (mClubMenuListener != null) mClubMenuListener.clickCart();
                break;
            case R.id.account_larger:
                if (mClubMenuListener != null) mClubMenuListener.clickAccount();
                break;
            case R.id.feedback:
                if (mClubMenuListener != null) mClubMenuListener.clickSupport();
                break;
        }
    }

    public void setListener(ClubMenuListener mClubMenuListener) {
        this.mClubMenuListener = mClubMenuListener ;
    }

//    public void setListener(OnClickListener onClickListener) {
//        homeLarger.setOnClickListener(onClickListener);
//        categoriesLarger.setOnClickListener(onClickListener);
//        cartLarger.setOnClickListener(onClickListener);
//        accountLarger.setOnClickListener(onClickListener);
//        feedback.setOnClickListener(onClickListener);
//    }

    public void setSelectMenu(int type) {
        Zlog.ii("lxm ss setSelectMenu:" + type);
        txtHome.setSelected(false);
        categoriesText.setSelected(false);
        cartText.setSelected(false);
        accountText.setSelected(false);
        txtSupport.setSelected(false);

        imgHome.setSelected(false);
        categoriesImg.setSelected(false);
        cartImg.setSelected(false);
        accountImg.setSelected(false);
        imgFeedback.setSelected(false);

        switch (type) {
            case MENU_TYPE_HOME: {
                if (txtHome.isSelected()) {
                    return;
                }
                Zlog.ii("lxm ss setSelectMenu:" + type);
                txtHome.setSelected(true);
                imgHome.setSelected(true);
            }
            break;
            case MENU_TYPE_CATEGORY: {
                if (categoriesText.isSelected()) {
                    return;
                }
                categoriesText.setSelected(true);
                categoriesImg.setSelected(true);
            }
            break;
            case MENU_TYPE_CART: {
                if (cartText.isSelected()) {
                    return;
                }
                cartText.setSelected(true);
                cartImg.setSelected(true);
            }
            break;
            case MENU_TYPE_ACCOUNT: {
                if (accountText.isSelected()) {
                    return;
                }
                accountText.setSelected(true);
                accountImg.setSelected(true);
            }
            break;
            case MENU_TYPE_SUPPORT: {
                if (txtSupport.isSelected()) {
                    return;
                }
                txtSupport.setSelected(true);
                imgFeedback.setSelected(true);
            }
            break;
            default:
                break;
        }
    }

    public boolean isMenuSelected(int type) {

        boolean isSelect = false;
        switch (type) {
            case MENU_TYPE_HOME: {
                if (txtHome.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            case MENU_TYPE_CATEGORY: {
                if (categoriesText.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            case MENU_TYPE_CART: {
                if (cartText.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            case MENU_TYPE_ACCOUNT: {
                if (accountText.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            case MENU_TYPE_SUPPORT: {
                if (txtSupport.isSelected()) {
                    isSelect = true;
                }
            }
            break;
            default:
                break;
        }
        return isSelect;
    }

    public void setMenuLanguage() {
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

        void clickCart();

        void clickHome();

        void clickCategories();

        void clickAccount();

        void clickSupport();
    }

}
