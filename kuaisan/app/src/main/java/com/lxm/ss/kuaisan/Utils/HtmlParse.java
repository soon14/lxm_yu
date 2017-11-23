package com.lxm.ss.kuaisan.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lxm on 2017/11/23.
 */

public class HtmlParse {




    public static void parseHtml() {

        try {
            //从一个URL加载一个Document对象。
            Document doc = Jsoup.connect("http://blog.csdn.net/u013101747/article/details/78616842").get();
            Element body = doc.body();
            Elements select = body.select("div.(container clearfix)");
            //打印 <a>标签里面的title


            Zlog.ii("lxm parseHtml:" + select.html());
        }catch(Exception e) {
            Zlog.ii("lxm parseHtml:Exception" +e.getMessage());
        }

    }


}
