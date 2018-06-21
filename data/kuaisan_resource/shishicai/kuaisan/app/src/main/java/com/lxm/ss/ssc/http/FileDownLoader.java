package com.lxm.ss.ssc.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件下载工具
 *
 * @author zhoulei on 2017/6/6.
 */

public class FileDownLoader {

    /**
     * @param listener  回调下载结果
     * @param path      下载的文件保存在本地的位置
     * @param urlString 文件在服务器的url
     */
    public FileDownLoader(String urlString, String path,String fileName , FileDownLoaderListener listener) {
        FileOutputStream fos = null;
        HttpURLConnection conn;
        try {
            URL url = new URL(urlString);

            conn = (HttpURLConnection) url.openConnection();//一定是这种类型
            //set cookie. sCookie is my static cookie string
//            conn.setRequestProperty("Cookie", CookieHelper.getCookieString());
            conn.setReadTimeout(30000);//设置客户端连接超时间隔，

            //判断服务端正常的反馈是否已经到达了 客户端
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                File file = new File(path);
                if (!file.exists() || !file.isDirectory()) {
                    file.mkdir();
                }

                fos = new FileOutputStream(new File(path + fileName));
                byte[] b = new byte[1024];
                int len;
                while ((len = is.read(b)) != -1) {  //先读到内存
                    fos.write(b, 0, len);
                }
                fos.flush();
                if (listener != null) {
                    listener.onSuccess();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFail(-1, e.getMessage());
            }
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public interface FileDownLoaderListener {
        void onSuccess();

        void onFail(int code, String msg);
    }

}
