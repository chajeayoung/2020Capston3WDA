package com.vote.vote.db.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "b_apply")
public class Audience {

    @Id
    @Column(nullable = false, name = "apply_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "B_APPLY_SEQ_GENERATOR")
    @SequenceGenerator(name = "B_APPLY_SEQ_GENERATOR", sequenceName = "B_APPLY_SEQ", allocationSize = 1)
    private int applyId;

    @Column(nullable = false, name = "a_title")
    private String aTitle; // 제목

    @Column(nullable = false, name = "a_content")
    private String aContent; // 내용41

    @Column(name = "a_date")
    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date aDate; // 등록일

    @Column(name = "a_mdate")
    @DateTimeFormat
    private Date aMdate; // 수정일

    @Column(name = "a_view_count")
    private int aViewCount; // 조회수

    @Column(name = "a_recruits")
    private int aRecruits; // 추첨인원

    @Column(name = "a_view")
    private String aView; // 결과공개여부(1자리)

    @Column(name = "a_limit")
    private int aLimit; // 신청횟수 제한

    @Column(name = "a_startdate")
    private Date aStartdate; // 응모시작일

    @Column(name = "a_enddate")
    private Date aEnddate; // 응모마감일

    @Column(nullable = false, name = "r_id")
    private int rId; // 작성자

    @Column(name = "a_price")
    private int aPrice; // 소모포인트

    @Column(name = "program_id")
    private int programId; // 프로그램

    @Column(name = "img")
    private String img; // 이미지

    @Column
    private int result;

    public int getApplyId() {
        return this.applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getATitle() {
        return this.aTitle;
    }

    public void setATitle(String aTitle) {
        this.aTitle = aTitle;
    }

    public String getAContent() {
        return this.aContent;
    }

    public void setAContent(String aContent) {
        this.aContent = aContent;
    }

    public Date getADate() {
        return this.aDate;
    }

    public void setADate(Date aDate) {
        this.aDate = aDate;
    }

    public Date getAMdate() {
        return this.aMdate;
    }

    public void setAMdate(Date aMdate) {
        this.aMdate = aMdate;
    }

    public int getAViewCount() {
        return this.aViewCount;
    }

    public void setAViewCount(int aViewCount) {
        this.aViewCount = aViewCount;
    }

    public int getARecruits() {
        return this.aRecruits;
    }

    public void setARecruits(int aRecruits) {
        this.aRecruits = aRecruits;
    }

    public String getAView() {
        return this.aView;
    }

    public void setAView(String aView) {
        this.aView = aView;
    }

    public int getALimit() {
        return this.aLimit;
    }

    public void setALimit(int aLimit) {
        this.aLimit = aLimit;
    }

    public Date getAStartdate() {
        return this.aStartdate;
    }

    public void setAStartdate(Date aStartdate) {
        this.aStartdate = aStartdate;
    }

    public Date getAEnddate() {
        return this.aEnddate;
    }

    public void setAEnddate(Date aEnddate) {
        this.aEnddate = aEnddate;
    }

    public int getRId() {
        return this.rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public int getAPrice() {
        return this.aPrice;
    }

    public void setAPrice(int aPrice) {
        this.aPrice = aPrice;
    }

    public int getProgramId() {
        return this.programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}