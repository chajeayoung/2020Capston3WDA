package com.vote.vote.db.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="audition_option_value")
public class AuditionOptionValue {
    
    @Id
    @Column(nullable=false, name="no")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITION_OPTION_VALUE_SEQ_GENERATOR")
    @SequenceGenerator(name="AUDITION_OPTION_VALUE_SEQ_GENERATOR", sequenceName="AUDITION_OPTION_VALUE_SEQ", allocationSize = 1)
    private int no;

    @Column(name="option_no")
    private int optionNo;

    @Column(name="audition_id")
    private int auditionId;

    @Column(name="audition_con")
    private int auditionCon;

    @Column
    private String value;


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

	public int getAuditionCon() {
		return auditionCon;
	}

	public void setAuditionCon(int auditionCon) {
		this.auditionCon = auditionCon;
	}
    
}