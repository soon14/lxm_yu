package com.lxm.ss.shishicai.http.model;

/**
 * Created by lxm on 2017/7/26.
 *
 * 针对新接口
 */

public class HttpResult {

    private String message ;

    private String body ;

    private int code ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "message='" + message + '\'' +
                ", body='" + body + '\'' +
                ", code=" + code +
                '}';
    }
}
