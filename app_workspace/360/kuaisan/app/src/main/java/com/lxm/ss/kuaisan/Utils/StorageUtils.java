package com.lxm.ss.kuaisan.Utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * 缓存文件位置
 * @author zhoulei on 2017/6/6.
 */

public class StorageUtils {

    public static String getPath(){
        return Environment.getExternalStorageDirectory().getPath();
    }


    private static boolean isSDCardExist(){
        return true;  //// TODO: 2017/6/6
    }

    public static String getStoragePath(Context context){
        String path;
        try {
            path = context.getFilesDir().getAbsolutePath().replace("files", "web") + File.separator;
        } catch (Exception e) {
            path = "/data/data/club.fromfactory/web/";
        }
        return path;
    }

    public static String getStorageWebResoucePath(Context context){
        String path;
        try {
            path = context.getFilesDir().getAbsolutePath().replace("files", "web") + File.separator;
        } catch (Exception e) {
            path = "/data/data/club.fromfactory/web/";
        }
        return path;
    }

    public static String getStorageHtmlFilePath(Context context){
        String path;
        try {
            path = context.getFilesDir().getAbsolutePath().replace("files", "html") + File.separator;
        } catch (Exception e) {
            path = "/data/data/club.fromfactory/html/";
        }
        return path;
    }

    public static void deleteHtmlFile(Context context) {
        String filePath = getStorageHtmlFilePath(context);
        deleteFolderFile(filePath);
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param
     * @return
     */
    public static void deleteFolderFile(String filePath) {
        try {
            if (StringUtils.isNotBlank(filePath)) {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                        file.delete();
                    }else {
                        File files[] = file.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            deleteFolderFile(files[i].getAbsolutePath());
                        }
                    }
                }else {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath){
        try {
            if (StringUtils.isNotBlank(filePath)) {
                File file = new File(filePath);

                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTotalCacheSize(Context context) throws Exception {

        long cacheSize = getFolderSize(context.getCacheDir());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            cacheSize += getFolderSize(context.getExternalCacheDir());

        }

        return getFormatSize(cacheSize);

    }



    public static void clearAllCache(Context context) {

        deleteFolderFile(context.getCacheDir().getPath(),true);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            deleteFolderFile(context.getExternalCacheDir().getPath());

        }

    }

    /**

     * 格式化单位

     *

     * @param size

     */

    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;

        if (kiloByte < 1) {

            return size + "Byte";

        }

        double megaByte = kiloByte / 1024;

        if (megaByte < 1) {

            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));

            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";

        }

        double gigaByte = megaByte / 1024;

        if (gigaByte < 1) {

            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));

            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";

        }

        double teraBytes = gigaByte / 1024;

        if (teraBytes < 1) {

            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));

            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";

        }

        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";

    }

    public static long getFolderSize(File file) throws Exception {

        long size = 0;

        try {

            File[] fileList = file.listFiles();

            for (int i = 0; i < fileList.length; i++) {

// 如果下面还有文件

                if (fileList[i].isDirectory()) {

                    size = size + getFolderSize(fileList[i]);

                } else {

                    size = size + fileList[i].length();

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return size;

    }

}
