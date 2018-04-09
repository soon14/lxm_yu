package com.lxm.ss.test.retrofif;

import java.util.List;

/**
 * Created by lxm on 2017/9/29.
 */

public class Categories {


    /**
     * body : [{"filter_title":"Sort","display_value":"Default","filter_content":["Default","Best Sellers","Trending","New Arrival"],"multiple_choice":false}]
     * message : ok
     * code : 0
     */

    private String message;
    private int code;
    private List<BodyBean> body;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * filter_title : Sort
         * display_value : Default
         * filter_content : ["Default","Best Sellers","Trending","New Arrival"]
         * multiple_choice : false
         */

        private String filter_title;
        private String display_value;
        private boolean multiple_choice;
        private List<String> filter_content;

        public String getFilter_title() {
            return filter_title;
        }

        public void setFilter_title(String filter_title) {
            this.filter_title = filter_title;
        }

        public String getDisplay_value() {
            return display_value;
        }

        public void setDisplay_value(String display_value) {
            this.display_value = display_value;
        }

        public boolean isMultiple_choice() {
            return multiple_choice;
        }

        public void setMultiple_choice(boolean multiple_choice) {
            this.multiple_choice = multiple_choice;
        }

        public List<String> getFilter_content() {
            return filter_content;
        }

        public void setFilter_content(List<String> filter_content) {
            this.filter_content = filter_content;
        }

        @Override
        public String toString() {
            return "BodyBean{" +
                    "filter_title='" + filter_title + '\'' +
                    ", display_value='" + display_value + '\'' +
                    ", multiple_choice=" + multiple_choice +
                    ", filter_content=" + filter_content +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Categories{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", body=" + body +
                '}';
    }
}
