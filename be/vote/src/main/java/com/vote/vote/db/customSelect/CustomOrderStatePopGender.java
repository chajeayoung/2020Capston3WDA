package com.vote.vote.db.customSelect;

import java.math.BigDecimal;

public class CustomOrderStatePopGender {
    private int popId;
    private String pName;
    private int count;
    private String gender;

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


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public CustomOrderStatePopGender(){}
    public CustomOrderStatePopGender(BigDecimal popId, String pName, BigDecimal count, String gender) {
        this.popId = popId.intValue();
        this.pName = pName;
        this.count = count.intValue();
        this.gender = gender;
    }
}