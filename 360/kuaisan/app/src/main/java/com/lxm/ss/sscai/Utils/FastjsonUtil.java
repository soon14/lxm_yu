package com.lxm.ss.sscai.Utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * Created by lxm on 2017/6/20.
 */

public class FastjsonUtil<T> {


    private static FastjsonUtil mFastjsonUtil;

    public static FastjsonUtil getInstance() {
        if (mFastjsonUtil == null) {
            mFastjsonUtil = new FastjsonUtil();
        }
        return mFastjsonUtil;
    }

    public T parseJson(String message, TypeReference<T> typeReference) {
        T t = null;
        try {
            t = JSONObject.parseObject(message, typeReference);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (t != null) {
            Zlog.ii("lxm fastjson parseJson :" + t.toString());
        }
        return t;
    }

    public String toJson(Object o) {
        String json = null;
        try {
            json = JSONObject.toJSONString(o,true).replace("\n","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (json != null) {
            Zlog.ii("lxm fastjson toJson :" + json);
        }

        return json;
    }
}
