package com.lxm.ss.kuaisan.Utils;


import com.lxm.ss.kuaisan.ui.trailer_infor.TrailerInfor;

import java.util.ArrayList;
import java.util.List;

import club.fromfactory.baselibrary.utils.StringUtils;

/**
 * Created by lxm on 2017/11/23.
 */

public class HtmlParse {

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
        String str1 = StringUtils.matchRepalce(reg, htmlUrl, "");

        Zlog.ii("lxm parseTrailerInfor:2  " + str1);
        String reg1 = "<li class=\"newsItem\">(.*?)</li><li class=\"newsItem\">" ;

        List<String> stringList = StringUtils.matchStrList(reg1, str1);

        for (int i = 0; i < stringList.size(); i++) {

            TrailerInfor trailerInfor = new TrailerInfor();

            String str = stringList.get(i);
            String regUrl = "href=\"(.*?)\"" ;
            String strUrl  = StringUtils.matchStrStr(regUrl,str);
            String regImg = "src=\"(.*?)\"" ;
            String strImg = StringUtils.matchStrStr(regImg,str);
            String regTitle = "<h2>(.*?)</h2>" ;
            String strTitle = StringUtils.matchStrStr(regTitle,str);
            String regContent = "<p>(.*?)</p>" ;
            String strContent = StringUtils.matchStrStr(regContent,str);
            String regTime = "<i class=\"mark2\">(.*?)</i>" ;
            String strTime = StringUtils.matchStrStr(regTime,str);

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
