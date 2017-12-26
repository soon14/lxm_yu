package com.lxm.ss.kuaisan.receiver;

import android.content.Context;

import com.lxm.ss.kuaisan.Utils.Zlog;

import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * Created by lxm on 2017/11/22.
 * 自定义JPush message 接收器,包括操作tag/alias的结果返回(仅仅包含tag/alias新接口部分)
 */

public class MyJPushMessageReceiver extends JPushMessageReceiver {

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);

        Zlog.ii("lxm MyJPushMessageReceiver:onTagOperatorResult " +jPushMessage.toString());
    }
    @Override
    public void onCheckTagOperatorResult(Context context,JPushMessage jPushMessage){
//        TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
        Zlog.ii("lxm MyJPushMessageReceiver:onCheckTagOperatorResult " +jPushMessage.toString());
    }
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
//        TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
        Zlog.ii("lxm MyJPushMessageReceiver:onAliasOperatorResult " +jPushMessage.toString());
    }
}
