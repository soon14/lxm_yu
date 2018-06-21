package com.lxm.ss.ssc.model;

import java.util.List;

/**
 * Created by lxm on 2017/11/28.
 */

public class RequestWYData {

    private String result ;

    private String totalBonus ;

    private List<LotterInfor> data ;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(String totalBonus) {
        this.totalBonus = totalBonus;
    }

    public List<LotterInfor> getData() {
        return data;
    }

    public void setData(List<LotterInfor> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "RequestWYData{" +
                "result='" + result + '\'' +
                ", totalBonus='" + totalBonus + '\'' +
                ", data=" + data +
                '}';
    }
}
