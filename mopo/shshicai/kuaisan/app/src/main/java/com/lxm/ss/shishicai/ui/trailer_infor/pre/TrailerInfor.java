package com.lxm.ss.shishicai.ui.trailer_infor.pre;

/**
 * Created by lxm on 2017/12/1.
 */

public class TrailerInfor {

    private String title ;

    private String imgUrl ;

    private String content ;

    private String urlLink ;

    private String time ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TrailerInfor{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", content='" + content + '\'' +
                ", urlLink='" + urlLink + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
