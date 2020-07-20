package com.vote.vote.db.customSelect;

import java.util.List;

import com.vote.vote.db.dto.PopularBoard;

public class CustomPopularBoard {
    private List<PopularBoard> popularBoard;
    private int count;

    public List<PopularBoard> getPopularBoard() {
        return popularBoard;
    }

    public void setPopularBoard(List<PopularBoard> popularBoard) {
        this.popularBoard = popularBoard;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}