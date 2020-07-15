package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "apply_result")
public class ApplyResult {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPLY_RESULT_SEQ_GENERATOR")
    @SequenceGenerator(name="APPLY_RESULT_SEQ_GENERATOR", sequenceName="APPLY_RESULT_SEQ", allocationSize = 1)
    private int no;

    @Column(name="apply_id")
    private int applyId;

    @Column
    private String phone;

    @Column
    private String name;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}