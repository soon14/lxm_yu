package com.lxm.ss.ssc.model;

/**
 * Created by lxm on 2017/11/28.
 *
 * //{
 "awardNo": "3 1 0 1 3 0 0 1 3 3 1 1 1 1",
 "awardTime": "2017-11-04 23:46:03",
 "extra": null,
 "gameEn": "football_9",
 "luckyBlue": "",
 "periodName": "17165",
 "totalPool": "0.00",
 "totalSale": "18038046.00"
 }
 */

public class LotterInfor {


    private String awardNo ;
    private String awardTime ;
    private String extra ;
    private String gameEn ; //
    private String luckyBlue ;
    private String periodName ;
    private String totalPool ;
    private String totalSale ;

    public String getAwardNo() {
        return awardNo;
    }

    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getGameEn() {
        return gameEn;
    }

    public void setGameEn(String gameEn) {
        this.gameEn = gameEn;
    }

    public String getLuckyBlue() {
        return luckyBlue;
    }

    public void setLuckyBlue(String luckyBlue) {
        this.luckyBlue = luckyBlue;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public String getTotalPool() {
        return totalPool;
    }

    public void setTotalPool(String totalPool) {
        this.totalPool = totalPool;
    }

    public String getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(String totalSale) {
        this.totalSale = totalSale;
    }


    @Override
    public String toString() {
        return "LotterInfor{" +
                "awardNo='" + awardNo + '\'' +
                ", awardTime='" + awardTime + '\'' +
                ", extra='" + extra + '\'' +
                ", gameEn='" + gameEn + '\'' +
                ", luckyBlue='" + luckyBlue + '\'' +
                ", periodName='" + periodName + '\'' +
                ", totalPool='" + totalPool + '\'' +
                ", totalSale='" + totalSale + '\'' +
                '}';
    }
}
