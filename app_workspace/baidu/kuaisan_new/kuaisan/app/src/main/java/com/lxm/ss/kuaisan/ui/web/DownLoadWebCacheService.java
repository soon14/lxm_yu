package com.lxm.ss.kuaisan.ui.web;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.lxm.ss.kuaisan.Utils.MDFive;
import com.lxm.ss.kuaisan.Utils.PreferenceUtils;
import com.lxm.ss.kuaisan.Utils.StorageUtils;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.http.FileDownLoader;
import com.lxm.ss.kuaisan.http.NetUtils;
import com.lxm.ss.kuaisan.web.CookieHelper;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import club.fromfactory.okhttp.OkHttpUtil;
import club.fromfactory.okhttp.callback.StringCallback;
import okhttp3.Call;

/**
 * webview资源文件下载
 */
public class DownLoadWebCacheService extends Service {
    /**
     * 获取web资源更新地址
     */
    private final String GET_WEB_CACHE = NetUtils.APP_MAIN_URL + "staticzip/fetch";


    private String fileName = "webcache.zip" ;


    public DownLoadWebCacheService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String mCookie = CookieHelper.getCookieString();
        OkHttpUtil.getInstance().httpGet(GET_WEB_CACHE, null, null,mCookie, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String enable = jsonObject.optString("enable");
                    String link = jsonObject.optString("link");
                    String md5 = jsonObject.optString("md5");
                    Zlog.ii("lxm DownLoadWebCacheService:onResponse " +jsonObject.toString());
                    boolean isEnable = (enable != null && enable.equals("1"));
                    if(!isEnable || link == null || md5 == null){
//                        PreferenceUtils.getInstance(getApplicationContext())
//                                .setBooleanValue(PreferenceUtils.ENABLE_WEB_CACHE,false);
                        Zlog.ii("lxm DownLoadWebCacheService:onResponse 1" );
                        stopSelf();
                    } else {
                        Zlog.ii("lxm DownLoadWebCacheService:onResponse 2" );
//                        PreferenceUtils.getInstance(getApplicationContext())
//                                .setBooleanValue(PreferenceUtils.ENABLE_WEB_CACHE,true);
                        String lastMd5 = PreferenceUtils.getInstance(getApplicationContext())
                                .getStringValue("web_cache");

                        if(lastMd5 != null && lastMd5.equals(md5)){
                            stopSelf();
                        } else {
                            Zlog.ii("lxm DownLoadWebCacheService:onResponse 3" );
                            downLoadFile(link, StorageUtils.getStorageWebResoucePath(getApplicationContext()),fileName, md5);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    stopSelf();
                }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    private void downLoadFile(final String url, final String path,final String fileName , final String md5) {
        Zlog.ii("lxm DownLoadWebCacheService:webcacha 3"+url +" " + path  + "  " +fileName );
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new FileDownLoader(url, path, fileName,new FileDownLoader.FileDownLoaderListener() {
                    @Override
                    public void onSuccess() {
                        Zlog.ii("lxm DownLoadWebCacheService:webcacha 3onSuccess" );
                        saveData(path, md5);
                        stopSelf();
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        Zlog.ii("lxm DownLoadWebCacheService:webcacha 3onFail" );
                        stopSelf();
                    }
                });
            }
        });
        thread.start();
    }


    private void saveData(String filePath, final String md5) {
        File fromFile = new File(filePath+fileName);
        File toFile = new File(StorageUtils.getStorageWebResoucePath(getApplicationContext())+"/style/");
        InputStream ins = null;

        try {
            ins = new FileInputStream(fromFile);
            if (!md5.equals(MDFive.getMD5(ins))) {   //文件不完整或者被篡改
                return;
            }
            unZipFolder(fromFile.getPath(),toFile.getPath());
            PreferenceUtils.getInstance(getApplicationContext()).setStringValue("web_cache",md5);
//            PreferenceUtils.getInstance(getApplicationContext())
//                    .setBooleanValue(PreferenceUtils.ENABLE_WEB_CACHE,true);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                 if (ins != null) {
                    ins.close();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


   	 /**
     *将ZIP压缩到路径 
     * @param zipFileString ZIP名称 
     * @param outPathString 路径为unZIP
     * @throws异常 
     */
    public static void unZipFolder(String zipFileString,String outPathString)throws Exception {
        File outFile = new File(outPathString);
        if(!outFile.exists()){
            outFile.mkdir();
        }

        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));
        ZipEntry zipEntry;
        String szName ="";
        while((zipEntry = inZip.getNextEntry())!= null){
            szName = zipEntry.getName();
            if(zipEntry.isDirectory()){
                //获取窗口小部件的文件夹名称  
                szName = szName.substring(0,szName.length() -  1);
                File folder = new File(outPathString + File.separator + szName);
                folder.mkdirs();
            } else {
                File file = new File(outPathString + File.separator + szName);
                file.createNewFile();
                //获取文件的输出流  
                FileOutputStream out = new FileOutputStream(file);
                int len;
                byte [] buffer = new byte [1024];
                //读取(len)字节到缓冲区  
                while((len = inZip.read(buffer))!= -1){
                    //从位置0的缓冲区写入(len)字节  
                    out.write(buffer, 0,len);
                    out.flush();
                }
                out.close();
            }
        }
        inZip.close();
    }
}
