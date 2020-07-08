package com.vote.vote.service.dto;

import java.util.Date;

public class KakaoPayReadyVO {
    private String tid;
    private String next_redirect_pc_url;
    private Date created_at;
    private BuyProductsDTO prd;
    private int sum;
    
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getNext_redirect_pc_url() {
        return next_redirect_pc_url;
    }

    public void setNext_redirect_pc_url(String next_redirect_pc_url) {
        this.next_redirect_pc_url = next_redirect_pc_url;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

	public BuyProductsDTO getPrd() {
		return prd;
	}

	public void setPrd(BuyProductsDTO prd) {
		this.prd = prd;
	}

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}