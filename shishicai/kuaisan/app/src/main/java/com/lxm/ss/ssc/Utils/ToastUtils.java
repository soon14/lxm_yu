package com.lxm.ss.ssc.Utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * toast 工具包
 *
 * @author zhoulei
 */
public class ToastUtils {

    public static void show(final Context context, final String toast) {
        if (context != null) {
            Handler handler = new Handler(context.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (StringUtils.isNotBlank(toast))
                        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
