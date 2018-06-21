package com.lxm.ss.ssc.widget;//package club.fromfactory.widget;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.drawable.Drawable;
//import android.text.TextUtils;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import club.fromfactory.R;
//import club.fromfactory.Utils.FontManager;
//import club.fromfactory.Utils.Zlog;
//
///**
// * Created by lxm on 2017/2/21.(废弃)
// */
//
//public class TitleLinearlayout extends LinearLayout {
//
//    @BindView(R.id.txt_title_left)
//    TextView txtTitleLeft;
//    @BindView(R.id.img_left)
//    ImageView imgLeft;
//    @BindView(R.id.img_right)
//    ImageView imgRight;
//    @BindView(R.id.txt_title)
//    TextView txtTitle;
//    private Context mContext;
//
//    public TitleLinearlayout(Context context) {
//        this(context, null);
//    }
//
//    public TitleLinearlayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.mContext = context;
//        init(attrs);
//    }
//
//    public TitleLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.mContext = context;
//        init(attrs);
//    }
//
//    @SuppressWarnings("ResourceType")
//    private void init(AttributeSet attrs) {
//        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TitleItem);
//
//        boolean isLeft = typedArray.getBoolean(R.styleable.TitleItem_isleft, true);
//        boolean isRight = typedArray.getBoolean(R.styleable.TitleItem_isright, true);
//        Drawable drawableLeft = typedArray.getDrawable(R.styleable.TitleItem_imageleft);
//        Drawable drawableRight = typedArray.getDrawable(R.styleable.TitleItem_imageright);
//        String title = typedArray.getString(R.styleable.TitleItem_title);
//        String titleleft = typedArray.getString(R.styleable.TitleItem_titleleft);
//        typedArray.recycle();
//
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_title, this);
//        ButterKnife.bind(view, this);
//
//        Zlog.ii("lxm ss scantitlelinearlayout:" + isLeft + " " + isRight + "  " + title + "  " + imgLeft);
//        if (isLeft) {
//            imgLeft.setVisibility(View.VISIBLE);
//        } else {
//            imgLeft.setVisibility(View.GONE);
//        }
//        if (isRight) {
//            imgRight.setVisibility(View.VISIBLE);
//        } else {
//            imgRight.setVisibility(View.GONE);
//        }
//        if (drawableLeft != null) {
//            imgLeft.setImageDrawable(drawableLeft);
//        }
//        if (drawableRight != null) {
//            imgRight.setImageDrawable(drawableRight);
//        }
//        if (!TextUtils.isEmpty(title)) {
//            txtTitle.setText(title);
//        } else {
//            txtTitle.setText("");
//        }
//        if (!TextUtils.isEmpty(title)) {
//            txtTitleLeft.setText(titleleft);
//            txtTitleLeft.setTypeface(FontManager.getTypeface(mContext, FontManager.FONTAWESOME));
//        } else {
//            txtTitleLeft.setText("");
//        }
//
//    }
//
//    public void setTitleLeft(String title) {
//        if (!TextUtils.isEmpty(title)) {
//            txtTitleLeft.setText(title);
//        }
//    }
//
//    public void setTitleCenter(String title) {
//
//        if (!TextUtils.isEmpty(title)) {
//            txtTitle.setText(title);
//        }
//    }
//
//    public void setOnListner(View.OnClickListener onListner) {
//        txtTitleLeft.setOnClickListener(onListner);
//        imgLeft.setOnClickListener(onListner);
//        imgRight.setOnClickListener(onListner);
//    }
//
//
//}
