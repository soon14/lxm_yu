package com.lxm.ss.ssc.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.ViewGroup;

import com.lxm.ss.ssc.FFApplication;
import club.fromfactory.baselibrary.utils.Base64;
import club.fromfactory.baselibrary.utils.DensityUtils;
import club.fromfactory.baselibrary.utils.ScreenUtils;
import club.fromfactory.fresco.FrescoUtils;
import club.fromfactory.fresco.view.FrescoImageView;

/**
 * Created by lxm on 2017/8/4.
 */

public class ImageUtils {

    public static void loadImage(FrescoImageView mImageView , String imageUrl , boolean isLocalPath,int defaultImg) {
        FrescoUtils.getInstance().getFrescoImageLoad()
                .setImageView(mImageView)
                .setUrl(imageUrl)
//                .setCornerRadius(0)
                .setLoadLocalPath(isLocalPath)
//                .setPostprocessor(new BlurPostprocessor(context, 10))
                .setDefaultImg(defaultImg)
                .load();
    }


    // 分类页的广告图片 750*240
    public static void setImageParams(FrescoImageView view) {

        int screenWidth = ScreenUtils.getScreenWidth(FFApplication.getInstance());
//        int width = screenWidth;
//        int height = screenWidth * 525 / 1242;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int width = view.getWidth();
        Zlog.ii("lxm ss setImageParams:" + screenWidth +"  "+width );
        if (width == 0) {
            width = screenWidth * 3 / 4 - DensityUtils.dp2px(FFApplication.getInstance(),20);
//            width = DensityUtils.dp2px(FFApplication.getInstance(),80);
//            return;
        }
        Zlog.ii("lxm ss setImageParams1:" + screenWidth +"  "+width );
        layoutParams.height = width * 240 /750 ;
    }

    public static void setMessageCenterImageParams(FrescoImageView view) {

        int screenWidth = ScreenUtils.getScreenWidth(FFApplication.getInstance());
//        int width = screenWidth;
//        int height = screenWidth * 525 / 1242;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int width = view.getWidth();
        Zlog.ii("lxm ss setImageParams:" + screenWidth +"  "+width );
        if (width == 0) {
            width = screenWidth - DensityUtils.dp2px(FFApplication.getInstance(),60);
//            return;
        }
        Zlog.ii("lxm ss setImageParams1:" + screenWidth +"  "+width );
        layoutParams.height = width * 240 /750 ;
    }

    /**
     *
     */
    public static Bitmap getBitmapByBase64(String pic){
        pic = "data:image/png;base64,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";
        //data:image/png;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAeAGQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD2Ksafxd4atp5IJ/EOkxTRMUkjkvY1ZGBwQQWyCD2rZrxr4nweHr/W7bwzYpoWm39zL9q1TU5lgjaCMnOCzAEuxbdgMGIAz8rEj8dy3C08VW9nUulvdW0S3bv+mrdkehOTiro9X/trSv7K/tX+07L+zv8An789fK+9t+/nH3uOvXin3eqafYT20F5fW1vNdNst45plRpWyBhQTljkjgeorL/snSNX8DS6No89t/Zk9m9pbywETIgwU3A5+Yg++cjrmvGVZvtVgdRvfOtvBd4Zb+82sWa4l1LDbs5LAxqX+UHnIJycV0YPLqWJc7Sa5W9La2s+X5uVotd3psKU3E9+tr20vfO+y3UM/kSmGXypA3lyDqjY6MO4PNQf21pX9q/2V/adl/aP/AD6eevm/d3fczn7vPTpzXAfByKXUdK1LxNeWqw3Oo3szLt3bCGbc7oGJ27mIQ4IBEKZBK5PLG00Sbx14sfxzqE9rd6fI82mTNfMkqxMzOhi+YglAFKKB1kYENgBbjlVP6xWouT/dpbK7bdloluk3rbWwud2T7nu1Fee/Ba+hu/htaW8W4tZTSwSEjALFvM49gJAPqD2wTwnjXR4NE8S3ul6Re31t4fnkin163tSPs9lDLJEoCr8xEpKs2AM7WUAbQ1RSypTxdTCSnZxb1tuk/XRtWsuraW7G52ipHvteX2fxgmn1GFrnwreWuhS3X2cau8wMKjeUDlguzGeuHIHOCcc9/oEtlP4d0ybTYDBYy2sT28RABSMqCoIBPIBHc14hJof9o6H4803RNXOk6Po91LJLowt/O8zykG1/OYhhvaFjt52478Z1yrC4epKrHER2aV3fS8nH7N/eva17x3u1u1OTVrH0BRWH4N1Gw1XwbpF3pkXk2ZtkSOHczeVsGwpuYAttKlcnrjPetyvGq03TqShLdNr7maJ3VwooorMYVlXnhjw/qN091faHplzcyY3zT2kbu2BgZJGTwAPwrVoq4VJwd4Np+Ta/JoVrkFnZWmnWqWtjaw21tHnZDBGERcnJwBwOST+NQRaLpUElxJDpllHJcyrNOyQKDLIrbldiB8zBvmBPIPNXqKPaTu3d676vX111CxBZ2Vpp1qlrY2sNtbR52QwRhEXJycAcDkk/jVW/0HSdTuFubzTraW6VNiXJjAmjHONkg+ZCCSQVIIJyOa0aKFUmpcybv3u7hZHMeCtB1DQoNZOpG287UdVnv1S2kaRUWQL8u5lUkgg9qvW/hTQ7fUtS1FdPSS71JSl3LOzTGRD1T5yQFxgbRgYAGMAY2aK1qYqrOcp3s5b202tb8l9wlFJWM3Q9B07w5po07SoXgtFYusTTPIFJ643kkDPOBxkk9Sax9W+G3g/XL9r6/wBDha5fO94neHeSSSzBGALEk5J5PrXVUUQxVeFR1YTak92m7v1DlTVrFXTtOs9J0+Gw0+2jtrWFdscUYwFHX8ycknqSSatUUVhKTk3KTu2MKKKKQz//2Q==
        Bitmap bitmap = null;
        try {
            pic  = pic.substring(pic.indexOf(","));
            byte[] decode = Base64.decode(pic);
//        byte[] decode =  android.util.Base64.decode(pic,android.util.Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap ;

    }

}
