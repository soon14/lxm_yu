package com.lxm.ss.shishicai.model;

/**
 * Created by lxm on 2017/11/22.
 */

public class AppShowData {

    public static final String STATUS_JUMPT_WEB = "1" ;
    public static final String STATUS_NO_JUMPT = "0" ;


    private String appId ;
    private String status ;
    private String url ;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AppShowData{" +
                "appId='" + appId + '\'' +
                ", status='" + status + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
