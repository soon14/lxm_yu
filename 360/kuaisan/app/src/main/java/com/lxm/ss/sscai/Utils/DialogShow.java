package com.lxm.ss.sscai.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by john on 2016/6/30.
 * 对话框
 */
public class DialogShow {
    private Context mContext;
    private DoPositive mdoPositive;
    public DialogShow(Context context){
        this.mContext = context;
    }
    public interface DoPositive{
        void pressYes();
    }

    public void setPositiveListener(DoPositive doPositive){
        this.mdoPositive = doPositive;
    }
    public void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(msg);
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mdoPositive.pressYes();
                    }
                });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();                    }
                });
        builder.show();
    }

}
