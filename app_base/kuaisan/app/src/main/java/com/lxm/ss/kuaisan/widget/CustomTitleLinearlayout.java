package com.lxm.ss.kuaisan.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxm.ss.kuaisan.R;
import com.lxm.ss.kuaisan.Utils.FontManager;
import com.lxm.ss.kuaisan.Utils.Zlog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lxm on 2017/2/21.
 */

public class CustomTitleLinearlayout extends LinearLayout {

    @BindView(R.id.txt_title_left)
    TextView txtTitleLeft;
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.txt_title_center)
    TextView txtTitleCenter;
    @BindView(R.id.ly_right)
    LinearLayout lyRight;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.layout_title_img_right)
    ImageView layoutTitleImgRight;

    private Context mContext;

    public CustomTitleLinearlayout(Context context) {
        this(context, null);
    }

    public CustomTitleLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    public CustomTitleLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(attrs);
    }

    //    public ScanTitleLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        this.mContext = context;
//        init(attrs);
//    }
    @SuppressWarnings("ResourceType")
    private void init(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TitleItemCustom);

        Drawable drawableLeft = typedArray.getDrawable(R.styleable.TitleItemCustom_imageleft1);
        String title = typedArray.getString(R.styleable.TitleItemCustom_title1);
        String titleleft = typedArray.getString(R.styleable.TitleItemCustom_titleleft1);
        Drawable drawableCenter = typedArray.getDrawable(R.styleable.TitleItemCustom_imagecenter1);
        Drawable drawableRight = typedArray.getDrawable(R.styleable.TitleItemCustom_imageright1);
        typedArray.recycle();

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_title_custom, this);

        ButterKnife.bind(view, this);

        Zlog.ii("lxm ss CustomTitleLinearlayout:" + "  " + title);
        if (drawableLeft != null) {
            imgLeft.setVisibility(View.VISIBLE);
            imgLeft.setImageDrawable(drawableLeft);
        } else {
            imgLeft.setVisibility(View.GONE);
        }

        if (drawableCenter != null) {
            logo.setVisibility(View.VISIBLE);
            logo.setImageDrawable(drawableCenter);
        } else {
            logo.setVisibility(View.GONE);
        }
        if (drawableRight != null) {
            layoutTitleImgRight.setVisibility(View.VISIBLE);
            layoutTitleImgRight.setImageDrawable(drawableCenter);
        } else {
            layoutTitleImgRight.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(title)) {
            txtTitleCenter.setText(title);
        } else {
            txtTitleCenter.setText("");
        }
        if (!TextUtils.isEmpty(titleleft)) {
            txtTitleLeft.setText(titleleft);
            txtTitleLeft.setTypeface(FontManager.getTypeface(mContext, FontManager.FONTAWESOME));
        } else {
            txtTitleLeft.setText("");
        }

        setRightVisible(false);
    }

    /**
     * @param validMethodName 执行的方法名
     */

    public void setRightView(String validMethodName) {

        Zlog.ii("lxm ss setRightView:" + validMethodName);
        try {
            Class cls = Class.forName("club.fromfactory.widget.CustomTitleLinearlayout");
            //public 方法
            Method method = CustomTitleLinearlayout.class.getDeclaredMethod(validMethodName, String.class);
            Zlog.ii("lxm ss setRightView:1" + validMethodName);
            method.invoke(CustomTitleLinearlayout.class.newInstance());
            Zlog.ii("lxm ss setRightView:2" + validMethodName);
            View view = (View) method.invoke(CustomTitleLinearlayout.class.newInstance());
            lyRight.addView(view);
            Zlog.ii("lxm ss setRightView:3" + validMethodName);
//            //私有方法
//            Method method = CustomTitleLinearlayout.class.getDeclaredMethod(validMethodName, String.class);
//            // 若调用私有方法，必须抑制java对权限的检查
//            method.setAccessible(true);
//            // 使用invoke调用方法，并且获取方法的返回值，需要传入一个方法所在类的对象，new Object[]
//            // {"Kai"}是需要传入的参数，与上面的String.class相对应
//            View View = (View) method.invoke(person,
//                    new Object[] { "Kai" });
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public View initSave() {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.title_right_save, null);
//        lyRight.addView(view);
//        return view;
//    }

//    public View cartFavorites() {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.title_right_favorite, null);
//        TextView txt_favorite = (TextView) view.findViewById(R.id.txt_favorite);
//
//        txt_favorite.setText(getResources().getString(R.string.favor));
//        lyRight.addView(view);
//        return view;
//    }

    //    public void setTitleLeft(String title) {
//        if (!TextUtils.isEmpty(title)) {
//            txtTitleLeft.setText(title);
//        }
//    }
    public void setLeftTextVisible(boolean isVisible) {
        if (isVisible) {
            txtTitleLeft.setVisibility(View.VISIBLE);
        } else {
            txtTitleLeft.setVisibility(View.GONE);
        }
    }


    public void setTitleCenter(String title) {
        if (!TextUtils.isEmpty(title)) {
            logo.setVisibility(View.GONE);
            txtTitleCenter.setVisibility(View.VISIBLE);

            txtTitleCenter.setText(title);
        } else {
            txtTitleCenter.setVisibility(View.GONE);
            logo.setVisibility(View.VISIBLE);
        }
    }

    public void setRightVisible(boolean isVisible) {
        if (isVisible) {
            lyRight.setVisibility(View.VISIBLE);
        } else {
            lyRight.setVisibility(View.GONE);

        }
    }

//    public void setOnListner(OnClickListener onListner) {
//        txtTitleLeft.setOnClickListener(onListner);
//        imgLeft.setOnClickListener(onListner);
//        lyRight.setOnClickListener(onListner);
//        txtTitleCenter.setOnClickListener(onListner);
//
//    }

    private CustomTitleListener mListener;

    public void setListener(CustomTitleListener listener) {
        mListener = listener;
    }

    @OnClick({R.id.txt_title_center, R.id.logo, R.id.img_left, R.id.txt_title_left, R.id.ly_right,R.id.layout_title_img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_title_center:
                if (mListener != null) {
                    mListener.clickCenter();
                }
                break;
            case R.id.logo:

                break;
            case R.id.img_left:
            case R.id.txt_title_left:
                if (mListener != null) {
                    mListener.clickLeft();
                }
                break;
            case R.id.layout_title_img_right:
            case R.id.ly_right:
                if (mListener != null) {
                    mListener.clickRight();
                }
                break;
        }
    }

    public abstract static class CustomTitleListener {

        public void clickLeft(){};

        public void clickCenter(){};

        public void clickRight(){};

    }


}
