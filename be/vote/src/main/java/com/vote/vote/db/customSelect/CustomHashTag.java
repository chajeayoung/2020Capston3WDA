package com.vote.vote.db.customSelect;

import java.math.BigDecimal;
import java.util.List;

import com.vote.vote.db.dto.HashTag;

public class CustomHashTag {
	private int rownum;
    private String hashTag;

	public String getHashTag() {
		return hashTag;
	}

	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public CustomHashTag(BigDecimal rownum, String hashTag) {
		this.rownum = rownum.intValue();
		this.hashTag = hashTag;
	}



    

    
}