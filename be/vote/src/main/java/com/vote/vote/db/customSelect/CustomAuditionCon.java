package com.vote.vote.db.customSelect;

import java.util.List;

import com.vote.vote.db.dto.AuditionCon;

public class CustomAuditionCon {
    
    private List<AuditionCon> auditionCon;
    private int count;

    public List<AuditionCon> getAuditionCon() {
        return auditionCon;
    }

    public void setAuditionCon(List<AuditionCon> auditionCon) {
        this.auditionCon = auditionCon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}