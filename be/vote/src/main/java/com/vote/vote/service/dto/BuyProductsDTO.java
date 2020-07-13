package com.vote.vote.service.dto;

import java.util.Arrays;

public class BuyProductsDTO {
	private int[] productIds;
	private int[] optionIds;
	private int[] quantitys;
	private String addr;
	private String addr2;
	private String receiver;
	private String phone;
	private int[] bagId;

	public int[] getOptionIds() {
		return optionIds;
	}

	public void setOptionIds(int[] optionIds) {
		this.optionIds = optionIds;
	}

	public int[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(int[] quantitys) {
		this.quantitys = quantitys;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int[] getBagId() {
		return bagId;
	}

	public void setBagId(int[] bagId) {
		this.bagId = bagId;
	}

	public BuyProductsDTO() {
	};

	public BuyProductsDTO(int[] productIds, int[] optionIds, int[] quantitys, String addr, String addr2,
			String receiver, String phone, int[] bagId) {
		this.productIds = productIds;
		this.optionIds = optionIds;
		this.quantitys = quantitys;
		this.addr = addr;
		this.addr2 = addr2;
		this.receiver = receiver;
		this.phone = phone;
		this.bagId = bagId;
	}

	public int[] getProductIds() {
		return productIds;
	}

	public void setProductIds(int[] productIds) {
		this.productIds = productIds;
	}

	@Override
	public String toString() {
		return "BuyProductsDTO [addr=" + addr + ", addr2=" + addr2 + ", bagId=" + Arrays.toString(bagId)
				+ ", optionIds=" + Arrays.toString(optionIds) + ", phone=" + phone + ", productIds="
				+ Arrays.toString(productIds) + ", quantitys=" + Arrays.toString(quantitys) + ", receiver=" + receiver
				+ "]";
	}
    
}