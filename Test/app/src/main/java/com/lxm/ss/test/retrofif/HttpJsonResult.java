package com.lxm.ss.test.retrofif;

/**
 * Created by lxm on 2016/11/18.
 *
 * 用于解析数据
 */

public class HttpJsonResult {

    private String country_code ;//国家码的列表
    private String rmd_words ;
    private String jsonrpc ;
    private String id ;
    private String result ;

    private boolean status ;

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getRmd_words() {
        return rmd_words;
    }

    public void setRmd_words(String rmd_words) {
        this.rmd_words = rmd_words;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HttpJsonResult{" +
                "country_code='" + country_code + '\'' +
                ", rmd_words='" + rmd_words + '\'' +
                ", jsonrpc='" + jsonrpc + '\'' +
                ", id='" + id + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
