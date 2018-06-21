package com.lxm.ss.ssc.base;

import android.app.Dialog;
import android.content.Context;

import com.lxm.ss.ssc.Utils.Zlog;

/**
 * Created by lxm on 2017/4/14.
 */

public class CustomDialog extends Dialog {


    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
            e.printStackTrace();
            Zlog.ii("lxm CustomDialog:show");
        }


    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
            Zlog.ii("lxm CustomDialog:dismiss");
        }
    }
}
