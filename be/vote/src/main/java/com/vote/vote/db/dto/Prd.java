package com.vote.vote.db.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;


@DynamicInsert
@Entity
@Table(name = "PRODUCT")
public class Prd {

    @Id
    @Column(name="product_id",nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_SEQ_GENERATOR")
    @SequenceGenerator(name="PRODUCT_SEQ_GENERATOR", sequenceName="PRODUCT_SEQ", allocationSize = 1)
    private int productId;
    @Column(name="PROGRAM_ID", nullable = true)
    private int programId;
    @Column(name="CATEGORY_ID", nullable = true)
    private int categoryId;
    @Column(name="P_NAME", nullable = true)
    private String name;
    @Column(name="P_PRICE", nullable = true)
    private int price;
    @Column(name="P_CONTENT", nullable = true)
    private String content;
    @Column(name="P_DETAIL", nullable = true)
    private String detail;
    @Column(name="P_UPLOAD", nullable = true)
    private String upload;
    @Column(name="P_STATE", nullable = true)
    private String state;

    @Column(name="P_ENDDATE", nullable = true)
    private String  endDate;

    @Column(name="P_MANAGER", nullable = true)
    private int manager;

    @Column(name="product_category_d", nullable = true)
    private int categoryD;
    
    @Column(name="P_STOCK", nullable= true)
    private int stock;

	@Column(name="IMAGE", nullable= false)
	private String img;
	@Column
	private int popId;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getManager() {
		return manager;
	}

	public void setManager(int manager) {
		this.manager = manager;
	}

	public int getCategoryD() {
		return categoryD;
	}

	public void setCategoryD(int categoryD) {
		this.categoryD = categoryD;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Prd [categoryD=" + categoryD + ", categoryId=" + categoryId + ", content=" + content + ", detail="
				+ detail + ", endDate=" + endDate + ", img=" + img + ", manager=" + manager + ", name=" + name
				+ ", price=" + price + ", productId=" + productId + ", programId=" + programId + ", state=" + state
				+ ", stock=" + stock + ", upload=" + upload + "]";
	}

	public int getPopId() {
		return popId;
	}

	public void setPopId(int popId) {
		this.popId = popId;
	}



   
}