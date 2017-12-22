package com.lxm.ss.ssc.parse.model;

import java.io.Serializable;

/**
 * Created by lxm on 2017/11/28.
 */

public class ScreenReg implements Serializable{


    private String regStr ;

    private boolean isScreen ; //是否筛选，true 表示获取筛选后的东西 matchScreenStr

    private String replace ;


    public String getRegStr() {
        return regStr;
    }

    public void setRegStr(String regStr) {
        this.regStr = regStr;
    }

    public boolean isScreen() {
        return isScreen;
    }

    public void setScreen(boolean screen) {
        isScreen = screen;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    @Override
    public String toString() {
        return "ScreenReg{" +
                "regStr='" + regStr + '\'' +
                ", isScreen=" + isScreen +
                ", replace='" + replace + '\'' +
                '}';
    }
}
