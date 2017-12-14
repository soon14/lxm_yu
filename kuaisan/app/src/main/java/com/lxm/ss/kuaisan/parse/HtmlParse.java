package com.lxm.ss.kuaisan.parse;

import com.lxm.ss.kuaisan.FFApplication;
import com.lxm.ss.kuaisan.Utils.Zlog;
import com.lxm.ss.kuaisan.http.MyOkHttp;
import com.lxm.ss.kuaisan.http.OkHttpRequestListener;
import com.lxm.ss.kuaisan.model.LotterInfor;
import com.lxm.ss.kuaisan.ui.betting.model.BettingAnalysisInfor;
import com.lxm.ss.kuaisan.ui.trailer_infor.pre.TrailerInfor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        String reg = "\\\\s*|\\t|\\r|\\n" ;
        String reg = "\\s*|\t|\r|\n";
        String str1 = StringUtils.matchReplace(reg, htmlUrl, "");

        Zlog.ii("lxm parseTrailerInfor:2  " + str1);
        String reg1 = "<liclass=\"newsItem\">(.*?)</li><liclass=\"newsItem\">" ;

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

    public static List<LotterInfor> parseLotterInfor(String htmlUrl) {

        List<LotterInfor> lotterInforsList = new ArrayList<>();

//        String reg = "\\s*|\t|\r|\n";
//        String str1 = StringUtils.matchReplace(reg, htmlUrl, "");

        Zlog.ii("lxm parseLotterInfor:1  " + htmlUrl);
        String reg1 = "<period>(.*?)</period>" ;

        List<String> stringList = StringUtils.matchStrList(reg1, htmlUrl);

        for (int i = 0; i < stringList.size(); i++) {

            LotterInfor lotterInfor = new LotterInfor();

            String str = stringList.get(i);
            Zlog.ii("lxm parseLotterInfor:2 " + str);

            String regperiodName = "<periodName>(.*?)</periodName>" ;

            String periodName = StringUtils.matchStrString(regperiodName, str);
            lotterInfor.setPeriodName(periodName);

            String regawardNo = "<awardNo>(.*?)</awardNo>" ;
            String awardNo = StringUtils.matchStrString(regawardNo, str);
            lotterInfor.setAwardNo(awardNo);

            String regawardTime = "<awardTime>(.*?)</awardTime>" ;
            String awardTime = StringUtils.matchStrString(regawardTime, str);
            lotterInfor.setAwardTime(awardTime);

            String regtotalsale = "<totalsale>(.*?)</totalsale>" ;
            String totalsale = StringUtils.matchStrString(regtotalsale, str);
            lotterInfor.setTotalSale(totalsale);

            String regtotalpool = "<totalpool>(.*?)</totalpool>" ;
            String totalpool = StringUtils.matchStrString(regtotalpool, str);
            lotterInfor.setTotalPool(totalpool);
            Zlog.ii("lxm parseLotterInfor:3  " +lotterInforsList);
            lotterInforsList.add(lotterInfor);

        }
        return lotterInforsList ;

    }

    public static List<BettingAnalysisInfor> parseBettingInfor(String htmlUrl) {

        List<BettingAnalysisInfor> bettingAnalysisInforList = new ArrayList<>();
        Zlog.ii("lxm parseBettingInfor:1  " + htmlUrl);
        String reg = "\\s*|\t|\\r|\n" ;

        String str1 = StringUtils.matchReplace(reg, htmlUrl, "");

        Zlog.ii("lxm parseBettingInfor:2  " + str1);
        String reg1 = "<thclass=\"new\">(.*?)<tdclass=\"icn\"></td>" ;

        List<String> stringList = StringUtils.matchStrList(reg1, str1);

        for (int i = 0; i < stringList.size(); i++) {

            Zlog.ii("lxm parseBettingInfor:3 " + stringList.get(i));
            BettingAnalysisInfor bettingAnalysisInfor = new BettingAnalysisInfor();

            String str = stringList.get(i);

            String regUrlAndTitle = "class=\"sxst\"(.*?)<divclass=\"info\">" ;
            String urlAndTitle = StringUtils.matchStrString(regUrlAndTitle,str);
            Zlog.ii("lxm parseBettingInfor :5 " + urlAndTitle);

            String regUrl = "<ahref=\"(.*?)\"target=\"_blank" ;
            String strUrl  = StringUtils.matchStrString(regUrl,urlAndTitle);
            Zlog.ii("lxm parseBettingInfor :6 " + strUrl);
            String regTitle = "target=\"_blank\"class=\"sxst\">(.*?)</a>" ;
            String strTitle = StringUtils.matchStrString(regTitle,urlAndTitle);
            Zlog.ii("lxm parseBettingInfor :7 " + strTitle);

            String regImg = "class=\"avatar\"><imgsrc=\"(.*?)\"/></a>" ;
            String strImg = StringUtils.matchStrString(regImg,str);



            String regContent = "<span>(.*?)</span>" ;
            String strContent = StringUtils.matchStrString(regContent,str);

            strContent = StringUtils.matchReplace(StringUtils.REMOVE_TAG,strContent,"");
//            String regTime = "<i class=\"mark2\">(.*?)</i>" ;
//            String strTime = StringUtils.matchStrString(regTime,str);

            bettingAnalysisInfor.setImgUrl(strImg);
            bettingAnalysisInfor.setTitle(strTitle);
            bettingAnalysisInfor.setUrl(strUrl);
            bettingAnalysisInfor.setContent(strContent);
            Zlog.ii("lxm parseBettingInfor:4  " +bettingAnalysisInfor);

            bettingAnalysisInforList.add(bettingAnalysisInfor);

        }
        return bettingAnalysisInforList ;
    }
    public static List<BettingAnalysisInfor> parseBettingTypeList(String htmlUrl) {

        List<BettingAnalysisInfor> bettingAnalysisInforList = new ArrayList<>();
        Zlog.ii("lxm parseBettingTypeList:1  " + htmlUrl);
        String reg = "\\s*|\t|\\r|\n" ;

        String str1 = StringUtils.matchReplace(reg, htmlUrl, "");

        Zlog.ii("lxm parseBettingTypeList:2  " + str1);
        String reg1 = "<divclass=\"fl_g_inner\">(.*?)<tdclass=\"fl_g\"width=\"24.9%\">" ;

        List<String> stringList = StringUtils.matchStrList(reg1, str1);

        String string = "" ;

        for (int i = 0; i < stringList.size(); i++) {

            Zlog.ii("lxm parseBettingTypeList:3 " +stringList.size() +"  "+ stringList.get(i));

            BettingAnalysisInfor bettingAnalysisInfor = new BettingAnalysisInfor();

            String str = stringList.get(i);

            String regUrl = "<ahref=\"(.*?)\"\">" ;
            String strUrl  = StringUtils.matchStrString(regUrl,str);

            String regTitle = "<dt>(.*?)</dt>" ;
            String strTitle = StringUtils.matchStrString(regTitle,str);
            strTitle = StringUtils.matchReplace(StringUtils.REMOVE_TAG,strTitle,"");
            Zlog.ii("lxm parseBettingTypeList :7 " + strTitle);
            String judgeType = "" ;
            if (strTitle.contains("(")) {
               judgeType =  strTitle.substring(0,strTitle.indexOf("("));
            }else {
                judgeType = strTitle ;
            }
            Zlog.ii("lxm parseBettingTypeList :8 " + judgeType);
            if (!FFApplication.getInstance().mapType.containsKey(judgeType)) {
                continue;
            }

            string += strTitle +"  " ;
            String regImg = "<imgsrc=\"(.*?)\"" ;
            String strImg = StringUtils.matchStrString(regImg,str);


            String regContent = "<em>(.*?)</em>" ;
            String strContent = StringUtils.matchStrString(regContent,str);
            strContent = StringUtils.matchReplace(StringUtils.REMOVE_TAG,strContent,"");

            bettingAnalysisInfor.setImgUrl(strImg);
            bettingAnalysisInfor.setTitle(strTitle);
            bettingAnalysisInfor.setUrl(strUrl);
            bettingAnalysisInfor.setContent(strContent);
            Zlog.ii("lxm parseBettingTypeList:4  " +bettingAnalysisInfor);
            Zlog.ii("lxm parseBettingTypeList:5  " +string);

            bettingAnalysisInforList.add(bettingAnalysisInfor);

        }
        return bettingAnalysisInforList ;
    }


}
