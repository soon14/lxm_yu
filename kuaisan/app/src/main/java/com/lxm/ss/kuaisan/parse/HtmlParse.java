package com.lxm.ss.kuaisan.parse;

import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.ui.trailer_infor.TrailerInfor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
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

                    String regMatch2 = "<[^>]*>";

                    Zlog.ii("lxm parserHtml:1" + str);

                    str =   StringUtils.matchReplace(regMatch2,str);

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



    //string regexstr = @"<[^>]*>"; //去除所有的标签
//@"<script[^>]*?>.*?</script >" //去除所有脚本，中间部分也删除
// string regexstr = @"<img[^>]*>"; //去除图片的正则
// string regexstr = @"<(?!br).*?>"; //去除所有标签，只剩br
// string regexstr = @"<table[^>]*?>.*?</table>"; //去除table里面的所有内容
//    string regexstr = @"<(?!img|br|p|/p).*?>"; //去除所有标签，只剩img,br,p


    public static List<TrailerInfor> parseTrailerInfor(String htmlUrl) {

        List<TrailerInfor> trailerInforList = new ArrayList<>();
        Zlog.ii("lxm parseTrailerInfor:1  " + htmlUrl);
        String reg = "\\\\s*|\\t|\\r|\\n" ;
        String str1 = StringUtils.matchReplace(reg, htmlUrl, "");

        Zlog.ii("lxm parseTrailerInfor:2  " + str1);
        String reg1 = "<li class=\"newsItem\">(.*?)</li><li class=\"newsItem\">" ;

        List<String> stringList = StringUtils.matchStrList(reg1, str1);

        for (int i = 0; i < stringList.size(); i++) {

            TrailerInfor trailerInfor = new TrailerInfor();

            String str = stringList.get(i);
            String regUrl = "href=\"(.*?)\"" ;
            String strUrl  = StringUtils.matchStrString(regUrl,str);
            String regImg = "src=\"(.*?)\"" ;
            String strImg = StringUtils.matchStrString(regImg,str);
            String regTitle = "<h2>(.*?)</h2>" ;
            String strTitle = StringUtils.matchStrString(regTitle,str);
            String regContent = "<p>(.*?)</p>" ;
            String strContent = StringUtils.matchStrString(regContent,str);
            String regTime = "<i class=\"mark2\">(.*?)</i>" ;
            String strTime = StringUtils.matchStrString(regTime,str);

            trailerInfor.setUrlLink(strUrl);
            trailerInfor.setTitle(strTitle);
            trailerInfor.setImgUrl(strImg);
            trailerInfor.setTime(strTime);
            trailerInfor.setContent(strContent);

            trailerInforList.add(trailerInfor);
            Zlog.ii("lxm parseTrailerInfor:3  " +trailerInfor);

        }
        return trailerInforList ;

    }


}
