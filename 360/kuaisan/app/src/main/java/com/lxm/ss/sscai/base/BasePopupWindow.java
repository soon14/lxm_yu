package com.lxm.ss.sscai.base;

import android.content.Context;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.PopupWindow;

import com.lxm.ss.sscai.Utils.Zlog;

/**
 * Created by lxm on 2017/9/27.
 */

public class BasePopupWindow extends PopupWindow implements KeyEvent.Callback,
		OnGestureListener {

	public BasePopupWindow(Context context) {
		super(context);
		BasePopupWindow.this.setFocusable(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Zlog.ii("keycode" + keyCode + "back" + KeyEvent.KEYCODE_BACK);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			dismiss();
		}
		return true;
	}

	@Override
	public boolean onKeyMultiple(int arg1, int arg2, KeyEvent event) {
		return true;
	}

	@Override
	public boolean onKeyUp(int arg1, KeyEvent event) {
		return true;
	}

	@Override
	public boolean onKeyLongPress(int arg1, KeyEvent event) {
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		Zlog.ii("on down----------------");
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}

