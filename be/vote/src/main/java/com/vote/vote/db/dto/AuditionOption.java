package com.vote.vote.db.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="audition_option")
public class AuditionOption {
    
    @Id
    @Column(nullable=false, name="no")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITION_OPTION_SEQ_GENERATOR")
    @SequenceGenerator(name="AUDITION_OPTION_SEQ_GENERATOR", sequenceName="AUDITION_OPTION_SEQ", allocationSize = 1)
    private int no;

    @Column(name="audition_id")
    private int auditionId;

    @Column
    private String name;
    

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getAuditionId() {
        return auditionId;
    }

    public void setAuditionId(int auditionId) {
        this.auditionId = auditionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    @Override
    public String toString() {
        return "AuditionOption [auditionId=" + auditionId + ", name=" + name + ", no=" + no + "]";
    }
}