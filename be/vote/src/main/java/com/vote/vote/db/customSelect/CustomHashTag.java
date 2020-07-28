package com.vote.vote.db.customSelect;

import java.math.BigDecimal;

public class CustomHashTag {
	private int rank;
	private int count;
    private String hashtag;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public CustomHashTag(String hashtag,BigDecimal count) {
		
		this.hashtag = hashtag;
		this.count = count.intValue();
	}

	public CustomHashTag(BigDecimal rank, String hashtag,BigDecimal count) {
		this.rank = rank.intValue();
		this.count = count.intValue();
		this.hashtag = hashtag;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}


    

    
}