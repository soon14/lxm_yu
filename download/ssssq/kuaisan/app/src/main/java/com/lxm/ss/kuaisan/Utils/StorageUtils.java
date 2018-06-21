package com.lxm.ss.kuaisan.Utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

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


}
