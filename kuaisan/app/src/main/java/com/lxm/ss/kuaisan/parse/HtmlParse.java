package com.lxm.ss.kuaisan.parse;

import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * Created by lxm on 2017/11/23.
 */

public class HtmlParse {


    public static void parseHtml() {

        try {
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.connect("http://cai.163.com/article/17/1123/11/D3U29IUO000597U8.html").timeout(60 * 1000).get();

            doc.select("");


            //打印 <a>标签里面的title

        }catch(Exception e) {
            Zlog.ii("lxm parseHtml:Exception" +e.getMessage());
        }

    }


    public static void parseHtml(String url) {

        MyOkHttp.getInstance().getHtml(url ,new OkHttpRequestListener() {
            @Override
            public void onSucceed(Object o) {
                super.onSucceed(o);

                if (o != null) {
                    String str = (String) o;
                    Zlog.ii("lxm parserHtml:" + str);

//                    String reg1 = "\\s*|\t|\r|\n";
                    String regMatch2 = "<[^>]*>";

//                    Pattern p = Pattern.compile(reg1);
//                    Matcher m = p.matcher(str);
//                    str=m.replaceAll("");


//                    str =   StringUtils.matchScreenStr(reg1,str);

                    Zlog.ii("lxm parserHtml:1" + str);

                    str =   StringUtils.matchScreenStr(regMatch2,str);

                    Zlog.ii("lxm parserHtml:2" + str);




                }else {
                }
            }

            @Override
            public void onFailed(int code, String body, String message) {
                super.onFailed(code, body, message);
            }
        });
    }


}
