package com.vote.vote.db.customSelect;

import java.math.BigDecimal;

public class CustomOrderStatePopAge {
    private int popId;
    private String pName;
    private int count;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public CustomOrderStatePopAge(){}
    public CustomOrderStatePopAge(BigDecimal popId, String pName, BigDecimal count, BigDecimal age) {
        this.popId = popId.intValue();
        this.pName = pName;
        this.count = count.intValue();
        this.age = age.intValue();
    }


}