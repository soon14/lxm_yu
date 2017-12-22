package com.lxm.ss.ssc.widget;

import java.util.ArrayList;
import java.util.List;

import com.lxm.ss.ssc.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class AutoRoll extends FrameLayout implements OnPageChangeListener {

  private ViewPager mvp;
//  private TextView mtitle;
  private LinearLayout mdotContainer;
//  private List<String> titles;
  private List<ImageView> ivs;
  private GestureDetector gestureDetector;
  private Context mContext ;

  public AutoRoll(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.mContext = context ;
    // 从xml中加载一个布局，填充到自己中
    View.inflate(getContext(), R.layout.arl_arl, this);
    init();
  }

  public AutoRoll(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public AutoRoll(Context context) {
    this(context, null);
  }

  private void init() {
    initView();
    // 获得viewpager的触摸事件
    mvp.setOnTouchListener(onTouchListener);
    gestureDetector = new GestureDetector(getContext(), gestureListener);
  }

  private OnTouchListener onTouchListener = new OnTouchListener() {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
      // 现成的类帮我们分析触摸事件
      gestureDetector.onTouchEvent(event);
      switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
          // viewPager.requestDisallowInterceptTouchEvent(true);
          // 停止滚动
          mHandler.removeCallbacks(mRunable);
          // handler.removeCallbacks(showNextPagerRunnable);
          // Log.d("onTouch", "down");
          break;
        case MotionEvent.ACTION_MOVE:
          // Log.d("onTouch", "move");
          break;
        // 当父控件在分发触摸事件时， 如果发现是自己需要的触摸事件，就把事件拦截掉了，子控件就无法收到后续的触摸事件了
        // 但父控件会发送一个cancel类型的事件给子控件， 对于子控件来说，cancle和up一样都是触摸事件的结束
        case MotionEvent.ACTION_CANCEL:
          // Log.e("onTouch", "CANCEL");
          // break; 此处不许要break，因为它的处理逻辑和UP类型是一样的
        case MotionEvent.ACTION_UP:
          // 恢复滚动
          mHandler.removeCallbacks(mRunable);
          mHandler.postDelayed(mRunable, 2000);
          // handler.postDelayed(showNextPagerRunnable,1000);
          // Log.d("onTouch", "up");
          break;

      }

      return false;
    }
  };
  private int clickedId = 0;
  private OnGestureListener gestureListener = new OnGestureListener() {

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
      // Toast.makeText(getContext(), "单机了", 0).show();
      // 告诉轮播图，你被点击了，
      AutoRoll.this.performClick();
      if (mOnPicClickListener != null) {
        mOnPicClickListener.OnClick(clickedId);
      }
      return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
      return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
      return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
      return false;
    }
  };

  private OnPicClickListener mOnPicClickListener;
  private Runnable mRunable = new MyRunnable();

  public void setmOnPicClickListener(OnPicClickListener mOnPicClickListener) {
    this.mOnPicClickListener = mOnPicClickListener;
  }

  public static interface OnPicClickListener {
    void OnClick(int id);
  }

  private void initView() {
    mvp = (ViewPager) findViewById(R.id.arl_viewpager);
//    mtitle = (TextView) findViewById(R.id.arl_textview);
    mdotContainer = (LinearLayout) findViewById(R.id.arl_dot_container);
  }

  /**
   * 暴露给使用者设置参数
   * 
   * @param imgUrls
   * @param titles
   */
  public void initData(List<String> imgUrls, List<String> titles) {
//    this.titles = titles;
    ivs = new ArrayList<ImageView>();
    if (imgUrls.size() == 0) {
      return;
    }
    mdotContainer.removeAllViews();
    for (int i = 0; i < imgUrls.size() + 2; i++) {
      ImageView iv = new ImageView(getContext());
      iv.setScaleType(ScaleType.FIT_XY);
      if (i == 0) {
        setAutoImage(mContext, iv, imgUrls.get(imgUrls.size() - 1));
      } else if (i == imgUrls.size() + 1) {
        setAutoImage(mContext, iv, imgUrls.get(0));
      } else {
        setAutoImage(mContext, iv, imgUrls.get(i - 1));
        addDot();
      }
      ivs.add(iv);
    }
    defaultSeclected();
    setMvp();
  }
  /**
   * 暴露给使用者设置参数
   *
   * @param imgUrls
   */
  public void initData(List<Drawable> imgUrls) {
//    this.titles = titles
    ivs = new ArrayList<ImageView>();
    if (imgUrls.size() == 0) {
      return;
    }
    mdotContainer.removeAllViews();
    for (int i = 0; i < imgUrls.size() + 2; i++) {
      ImageView iv = new ImageView(getContext());
      iv.setScaleType(ScaleType.FIT_XY);
      if (i == 0) {
        setAutoImage(mContext, iv, imgUrls.get(imgUrls.size() - 1));
      } else if (i == imgUrls.size() + 1) {
        setAutoImage(mContext, iv, imgUrls.get(0));
      } else {
        setAutoImage(mContext, iv, imgUrls.get(i - 1));
        addDot();
      }
      ivs.add(iv);
    }
    defaultSeclected();
    setMvp();
  }


  private void setAutoImage(Context mContext, ImageView mImageView, Drawable drawable) {
    mImageView.setImageDrawable(drawable);
  }
  private void setAutoImage(Context mContext, ImageView mImageView, String imageUrl) {


  }

  private void setMvp() {
    mvp.setAdapter(new MyAdapter());
    mvp.setCurrentItem(1);
    mvp.setOnPageChangeListener(this);
    mHandler.removeCallbacks(mRunable);
    mHandler.postDelayed(mRunable, 2000);
  }

  private void defaultSeclected() {
    mdotContainer.getChildAt(0).setEnabled(false);
//    if (titles == null || "".equals(titles)) {
//      mtitle.setText("");
//    } else {
//      mtitle.setText(titles.get(0));
//    }
  }

  /**
   * 设置点
   */
  private void addDot() {
    // 把10dp 转成对应的像素
    int dotWidth =
        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources()
            .getDisplayMetrics());
    View dotView = new View(getContext());
    // 设置宽高、marging
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dotWidth, dotWidth);
    lp.setMargins(0, 0, dotWidth, 0);
    dotView.setLayoutParams(lp);
    // 指定背景是选择器，在pagechangelistener中只去改变状态，更加面向对象，易于控制
    dotView.setBackgroundResource(R.drawable.arl_ball_bg_selector);
    mdotContainer.addView(dotView);
  }

  private class MyRunnable implements Runnable {

    @Override
    public void run() {
      mvp.setCurrentItem(mvp.getCurrentItem() + 1);
      mHandler.removeCallbacks(mRunable);
      mHandler.postDelayed(this, 2000);
    }
  }

  private Handler mHandler = new Handler();

  private class MyAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return ivs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      container.addView(ivs.get(position));
      return ivs.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView(ivs.get(position));
    }

  }

  @Override
  public void onPageScrollStateChanged(int state) {
    // OnScrollListener.SCROLL_STATE_FLING 2: 猛动,欻一下
    // OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:1 触摸滚动
    // OnScrollListener.SCROLL_STATE_IDLE:0 静止
    if (state == OnScrollListener.SCROLL_STATE_IDLE) {
      // Log.i("test", "静止了");
      if (pos == ivs.size() - 2 || pos == 1) {
        // 参数2:smoothScroll 平滑的滚动,顺滑的滚动
        mvp.setCurrentItem(pos, false);
      }
    }

  }

  @Override
  public void onPageScrolled(int arg0, float arg1, int arg2) {

  }

  private int prePosition = 1;
  private int pos = 0;

  @Override
  public void onPageSelected(int position) {
    // Log.i("test", "selected");
    pos = position;
    if (pos == 0) {
      pos = ivs.size() - 2;
    } else if (pos == ivs.size() - 1) {
      pos = 1;
    }
    // Log.i("test", "改过的pos:" + pos + ", prePosition:" + prePosition);
    if (prePosition != pos) {
//      mtitle.setText(titles.get(pos - 1));
      mdotContainer.getChildAt(pos - 1).setEnabled(false);
      mdotContainer.getChildAt(prePosition - 1).setEnabled(true);
      prePosition = pos;
      clickedId = pos;
    }
  }

  /**
   * 得到当前图片的position
   * 
   * @return
   */
  public int getCurrentIndex() {
    if (pos == 0) {
      return pos ;
    }
    return pos - 1;
  }

  /**
   * 设置不可见暂停滚动
   */
  public void onArlPause() {
    mHandler.removeCallbacks(mRunable);
  }

  /**
   * 设置可见滚动
   */
  public void onArlResume() {
    // 防止未移除重复添加
    mHandler.removeCallbacks(mRunable);
    mHandler.postDelayed(mRunable, 2000);
  }
}
