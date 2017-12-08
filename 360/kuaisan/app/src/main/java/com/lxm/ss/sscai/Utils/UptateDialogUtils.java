package com.lxm.ss.sscai.Utils;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.lxm.ss.sscai.R;
import com.lxm.ss.sscai.base.CustomDialog;

/**
 * Created by lxm on 2016/11/21.
 */

public class UptateDialogUtils {

    private static UptateDialogUtils dialogUtils;

    private UptateDialogUtils() {
    }

    public static UptateDialogUtils getInstance() {
        if (dialogUtils == null) {
            dialogUtils = new UptateDialogUtils();
        }
        return dialogUtils;
    }

    /**
     * 获取加载页的对话框
     *
     * @param context
     * @return
     */
    public CustomDialog getUptateDialog(Context context, boolean isForce, View.OnClickListener onSaveClickListener,
                                        View.OnClickListener onCancelClickListener) {
        final CustomDialog dialog = new CustomDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_popup_window);

        dialog.setCanceledOnTouchOutside(false);
//        dialog.setCancelable(false);
        Button btnSave = (Button) dialog.findViewById(R.id.save);
        btnSave.setOnClickListener(onSaveClickListener);
        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(onCancelClickListener);

        TextView editText = (TextView) dialog.findViewById(R.id.editText);

        if (isForce) {
            btnCancel.setVisibility(View.GONE);
        }
        editText.setText(context.getResources().getString(R.string.update_message_prompt));
        btnCancel.setText(context.getResources().getString(R.string.update_cancel));
        btnSave.setText(context.getResources().getString(R.string.update_btn));

        return dialog;
    }


}
