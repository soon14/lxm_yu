package club.fromfactory.baselibrary.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lxm on 2017/8/4.
 */

public class StringUtils {
//    public static final String REPORT_MEMBER_CONSUME_SEARCH_KEY = "^[0-9a-zA-Z\\u4e00-\\u9fa5\\s]{1,50}$";  // 必须为1-50个字符(中文、数字和英文)
//    public static final String PHONE_REGEX = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";   // 手机号;
//    public static final String TRUE_NAME = "^[0-9a-zA-Z\\u4e00-\\u9fa5\\s]{2,15}$";  // 必须为2-15个字符(中文、数字和英文)
//    public static final String USER_NAME = "^[0-9a-zA-Z\\u4e00-\\u9fa5\\-_\\s]{3,25}$";  // 必须为3-25个字符(支持中文、英文、数字及-和_)
//    //public static final String PASSWD = "^(?![a-z]+$)(?![A-Z]+$)(?![0-9]+$)[0-9a-zA-Z\\W]\\S{6,20}$";  // 6-20位字符，包含字母、数字或符号中两种
//    public static final String PASSWD = "^[0-9a-zA-Z\\W]\\S{5,20}$";  // 6-20位字符，支持字母、数字或符号的组合
//    public static final String DEVICE_NO = "^[0-9a-zA-Z]{1,6}$";  // 1-6位字符，支持字母、数字
//    public static final String DITITAL = "^[0-9.]+$";  //

    public static boolean isNotBlank(String str) {
        return str != null && str.length() > 0 && (!str.trim().equalsIgnoreCase("null")) ;
    }

    public static boolean isNull(String str) {
        return str == null || str.length() == 0 || str.trim().equalsIgnoreCase("null");
    }

    public static int[] stringArrToIntArr(String[] strArr) {
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    public static ArrayList<String> stringToStrArr(String str) {
        ArrayList<String> list = new ArrayList();
        String[] strArr = str.split(",");
        for (int i = 0; i < strArr.length; i++) {
            list.add(strArr[i]);
        }
        return list;
    }

    public static String removeStr(String removeStr, String oldStr) {
        Log.d("oldstr", oldStr + "-------" + removeStr);
        String[] mArr = oldStr.split(",");
        ArrayList<String> mList = new ArrayList();
        for (int i = 0; i < mArr.length; i++) {
            mList.add(mArr[i]);
        }
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).equals(removeStr)) {
                mList.remove(i);
            }
        }
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < mList.size(); i++) {
            if (!(i == mList.size() - 1)) {
                newStr.append(mList.get(i) + ",");
            } else {
                newStr.append(mList.get(i));
            }
        }
        Log.d("oldstr", newStr.toString() + "---");
        return newStr.toString();
    }



    public static ArrayList<String> stringToList(String str, boolean isReverse) {
        ArrayList<String> list = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return list;
        }
        String[] strArr = str.split(",");
        if (isReverse) {
            for (int i = strArr.length - 1; i >= 0; i--) {
                list.add(strArr[i]);
            }
        } else {
            for (int i = 0; i < strArr.length; i++) {
                list.add(strArr[i]);
            }
        }

        return list;
    }

    public static String listToString(ArrayList<String> stringList) {

        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < stringList.size(); i++) {
            newStr.append(stringList.get(i));
            if (i != stringList.size() - 1) {
                newStr.append(",");
            }
        }
        return newStr.toString();
    }

    /**
     * 获得字符串的长度，中文算2个，英文算1个
     *
     * @param text
     * @return
     */
    public static int getStringLength(String text) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < text.length(); i++) {
            String temp = text.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static String stringArrayToString(String[] str) {
        if (str == null) return null;
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < str.length; i++) {
            stringBuffer.append(str[i]);
            if (i != str.length - 1) stringBuffer.append(",");
        }

        return stringBuffer.toString();
    }

    public static int toInt(String value) {
        try {
            if (isNotBlank(value)) {
                return Integer.parseInt(value);
            }
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static float toFloat(String value) {
        try {
            if (isNotBlank(value)) {
                return Float.parseFloat(value);
            }
            return 0.0f;
        } catch (NumberFormatException e) {
            return 0.00f;
        }
    }

    public static double toDouble(String value) {
        try {
            if (isNotBlank(value)) {
                return Double.parseDouble(value);
            }
            return 0.0f;
        } catch (NumberFormatException e) {
            return 0.00f;
        }
    }
}
