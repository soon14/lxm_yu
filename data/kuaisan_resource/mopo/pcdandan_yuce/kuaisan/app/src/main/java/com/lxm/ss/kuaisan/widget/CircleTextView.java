package com.lxm.ss.kuaisan.widget;//package club.fromfactory.widget;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.graphics.Canvas;
//import android.graphics.Typeface;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//import android.widget.FrameLayout;
//import android.widget.TabWidget;
//import android.widget.TextView;
//
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.PaintFlagsDrawFilter;
//
//
///**
// * Created by lxm on 2017/7/3.
// */
//
//public class CircleTextView extends TextView {
//
//    public static final int POSITION_TOP_LEFT = 1;
//    public static final int POSITION_TOP_RIGHT = 2;
//    public static final int POSITION_BOTTOM_LEFT = 3;
//    public static final int POSITION_BOTTOM_RIGHT = 4;
//    public static final int POSITION_CENTER = 5;
//
//    private int badgeMarginH;
//    private int badgeMarginV;
//
//    private Paint mBgPaint = new Paint();
//    PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
//
//    public CircleTextView(Context context, AttributeSet attrs, int defStyle) {
//        this(context,attrs,defStyle, null, 1);
//        mBgPaint.setColor(Color.RED);
//        mBgPaint.setAntiAlias(true);
//    }
//
//    public CircleTextView(Context context, AttributeSet attrs) {
//        this(context, attrs,0);
//
//    }
//
//    public CircleTextView(Context context) {
//        this(context,null);
//    }
//    public CircleTextView(Context context,View target,int badgePosition) {
//        this(context,null,0,target,badgePosition);
//    }
//    public CircleTextView(Context context,AttributeSet attrs, int defStyle,View target,int badgePosition) {
//        super(context,attrs,defStyle);
//        this.init(context,target,badgePosition);
//    }
//
//    private void init(Context context,View target,int badgePosition) {
//
//        this.setTypeface(Typeface.DEFAULT_BOLD);
//
//
//        ViewGroup.LayoutParams lp = target.getLayoutParams();
//        ViewParent parent = target.getParent();
//
//        FrameLayout container = new FrameLayout(context);
//
//        if(target instanceof TabWidget) {
//            target = ((TabWidget)target).getChildTabViewAt(0);
//            ((ViewGroup)target).addView(container, new ViewGroup.LayoutParams(-1, -1));
//            this.setVisibility(View.GONE);
//            container.addView(this);
//        } else {
//            ViewGroup group = (ViewGroup)parent;
//            int index = group.indexOfChild(target);
//            group.removeView(target);
//            group.addView(container, index, lp);
//            container.addView(target);
//            this.setVisibility(View.GONE);
//            container.addView(this);
//            group.invalidate();
//
//        }
////        applyLayoutParams(badgePosition);
//    }
//
//
//    @Override
//    public void setLayoutParams(ViewGroup.LayoutParams params) {
//        super.setLayoutParams(params);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int measuredWidth = getMeasuredWidth();
//        int measuredHeight = getMeasuredHeight();
//        int max = Math.max(measuredWidth, measuredHeight);
//        setMeasuredDimension(max, max);
//    }
//
//    @Override
//    public void setBackgroundColor(int color) {
//        mBgPaint.setColor(color);
//    }
//
//    /**
//     * 设置显示的数量
//     *
//     * @param text
//     */
//    public void setNotifiText(int text) {
//        setText(text + "");
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        canvas.setDrawFilter(pfd);//给Canvas加上抗锯齿标志
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2, mBgPaint);
//        super.draw(canvas);
//    }
//
//    private void applyLayoutParams(int badgePosition) {
//        android.widget.FrameLayout.LayoutParams lp = new android.widget.FrameLayout.LayoutParams(-2, -2);
//        switch(badgePosition) {
//            case POSITION_TOP_LEFT:
//                lp.gravity = 51;
//                lp.setMargins(this.badgeMarginH, this.badgeMarginV, 0, 0);
//                break;
//            case POSITION_TOP_RIGHT:
//                lp.gravity = 53;
//                lp.setMargins(0, this.badgeMarginV, this.badgeMarginH, 0);
//                break;
//            case POSITION_BOTTOM_LEFT:
//                lp.gravity = 83;
//                lp.setMargins(this.badgeMarginH, 0, 0, this.badgeMarginV);
//                break;
//            case POSITION_BOTTOM_RIGHT:
//                lp.gravity = 85;
//                lp.setMargins(0, 0, this.badgeMarginH, this.badgeMarginV);
//                break;
//            case POSITION_CENTER:
//                lp.gravity = Gravity.CENTER;
//                lp.setMargins(0, 0, 0, 0);
//        }
//
//        this.setLayoutParams(lp);
//    }
//    private int dipToPixels(int dip) {
//        Resources r = this.getResources();
//        float px = TypedValue.applyDimension(1, (float)dip, r.getDisplayMetrics());
//        return (int)px;
//    }
//}
