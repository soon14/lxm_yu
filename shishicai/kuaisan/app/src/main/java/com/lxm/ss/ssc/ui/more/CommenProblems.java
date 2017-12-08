package com.lxm.ss.ssc.ui.more;

import java.util.List;

/**
 * Created by lxm on 2017/12/4.
 */

public class CommenProblems {


    private String problems;

    private String url ;

    private List<String> regList ;

    private boolean isTitle ;


    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }


    public List<String> getRegList() {
        return regList;
    }

    public void setRegList(List<String> regList) {
        this.regList = regList;
    }

    @Override
    public String toString() {
        return "CommenProblems{" +
                "problems='" + problems + '\'' +
                ", url='" + url + '\'' +
                ", regList=" + regList +
                ", isTitle=" + isTitle +
                '}';
    }
}
