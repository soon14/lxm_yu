package com.lxm.ss.ssc.Utils;

import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.lxm.ss.ssc.R;
import com.lxm.ss.ssc.base.CustomDialog;
import club.fromfactory.baselibrary.utils.ScreenUtils;

/**
 * Created by lxm on 2016/11/21.
 *
 * 全局加载进度条
 */

public class DialogUtils {


    private static DialogUtils dialogUtils;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils();
        }
        return dialogUtils;
    }

    /**
     * 获取加载页的对话框
     *
     * @param context
     * @return
     */
    public CustomDialog getProgressDialog(Context context) {

        final CustomDialog loadingDialog = new CustomDialog(context, R.style.Loading_dialog);
        loadingDialog.setContentView(R.layout.loading_dialog_webview);// 设置布局
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ScreenUtils.getScreenWidth(context);
        lp.height = ScreenUtils.getScreenHeight(context);

        return loadingDialog;
    }

}
