package club.fromfactory.baselibrary.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 屏幕分辨率相关
 * Created by lxm on 2017/8/4.
 */
public class DensityUtils {

    private DensityUtils()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dpתpx
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, int dpVal)
    {
        //        //密度因子
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpVal * scale + 0.5f);
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * spתpx
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * pxתdp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static int px2dp(Context context, int pxVal)
    {
               final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal / scale + 0.5f);
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (pxVal / scale);
    }

    /**
     * pxתsp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal)
    {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
