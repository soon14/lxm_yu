package com.lxm.ss.kuaisan.Utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Yun on 4/1/16.
 */
public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

}