package com.vote.vote.db.customSelect;

import java.math.BigDecimal;

public class CustomOrderStatePop {
    private int popId;
    private String pName;
    private String img;
    private int count;

    public int getPopId() {
        return popId;
    }

    public void setPopId(int popId) {
        this.popId = popId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CustomOrderStatePop(BigDecimal popId, String pName, String img, BigDecimal count) {
        this.popId = popId.intValue();
        this.pName = pName;
        this.img = img;
        this.count = count.intValue();
    }

   
}