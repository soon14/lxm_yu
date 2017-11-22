package com.lxm.ss.kuaisan.ui.web.model;

/**
 * Created by lxm on 2017/10/13.
 *
 * {"content_id":"34343,43434","content_type":"34343,45454","amount":500}
 */

public class JavaScriptData {

    private String content_id ;

    private String content_type ;

    private double amount ;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "JavaScriptData{" +
                "content_id='" + content_id + '\'' +
                ", content_type='" + content_type + '\'' +
                ", amount=" + amount +
                '}';
    }
}
