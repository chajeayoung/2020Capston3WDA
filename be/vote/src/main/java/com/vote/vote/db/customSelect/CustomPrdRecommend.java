package com.vote.vote.db.customSelect;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.math.BigDecimal;

public class  CustomPrdRecommend {
    
    private int rnum;
    private int productId;
    private String name;
    private String img;
    private int sum;
    // public CustomOrderState(BigDecimal product_id, String name,String image , BigDecimal sum, BigDecimal one, BigDecimal two, BigDecimal three, BigDecimal four, BigDecimal five) {
    //     this.product_id = product_id.intValue();
    //     this.image = image;
    //     this.name = name;
    //     this.sum = sum.intValue();
    //     this.one = one.intValue();
    //     this.two = two.intValue();
    //     this.three = three.intValue();
    //     this.four = four.intValue();
    //     this.five = five.intValue();
    // }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public CustomPrdRecommend(BigDecimal rnum,BigDecimal productId, String name, String img, BigDecimal sum) {
        this.rnum = rnum.intValue();
        this.productId = productId.intValue();
        this.name = name;
        this.img = img;
        this.sum = sum.intValue();
    }

    public int getRnum() {
        return rnum;
    }

    public void setRnum(int rnum) {
        this.rnum = rnum;
    }

 
}