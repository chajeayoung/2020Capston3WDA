package com.vote.vote.db.customSelect;

import java.math.BigDecimal;

public class CustomAuditionOption {

    private int optionNo;
    private int auditionId;
    private String name;
    private String value;
    private int conId;

    public int getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(int optionNo) {
        this.optionNo = optionNo;
    }

    public int getAuditionId() {
        return auditionId;
    }

    public void setAuditionId(int auditionId) {
        this.auditionId = auditionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public CustomAuditionOption(BigDecimal optionNo, BigDecimal auditionId, String name, String value, BigDecimal conId) {
        this.optionNo = optionNo.intValue();
        this.auditionId = auditionId.intValue();
        this.name = name;
        this.value = value;
        this.conId = conId.intValue();
    }
    
}