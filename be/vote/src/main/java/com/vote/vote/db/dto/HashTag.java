package com.vote.vote.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="hashtag")
public class HashTag {
    @Id
    @Column(name="no", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HASHTAG_SEQ_GENERATOR")
    @SequenceGenerator(name="HASHTAG_SEQ_GENERATOR", sequenceName="HASHTAG_SEQ", allocationSize = 1)
    private int no;

    @Column(name="POPULAR_ID") //인기인 이름
    private int popularid;

    @Column(name="P_ID") //인기인 이미지
    private int pid;

    @Column(name="HASHTAG")
    private String hashtag;

    public int getNo() {
        return no;
     }
  
     public void setNo(int no) {
        this.no = no;
     }
  
     public int getPopularid() {
        return popularid;
     }
  
     public void setPopularid(int popularid) {
        this.popularid = popularid;
     }
  
     public int getPid() {
        return pid;
     }
  
     public void setPid(int pid) {
        this.pid = pid;
     }
  
     public String getHashtag() {
        return hashtag;
     }
  
     public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
     }
  
     public HashTag(int no, int popularid, int pid, String hashtag) {
        this.no = no;
        this.popularid = popularid;
        this.pid = pid;
        this.hashtag = hashtag;
     }
  
     public HashTag() {
     
     }
     
     
     public HashTag(String hashtag) {
        this.hashtag = hashtag;
     }
        
	// public HashTag(int no, int popularid, int hotclibid, String hashtag) {
	// 	this.no = no;
	// 	this.popularid = popularid;
	// 	this.hotclibid = hotclibid;
	// 	this.hashtag = hashtag;
	// }

	// public HashTag() {
	
	// }
	
	
	// public HashTag(String hashtag) {
	// 	this.hashtag = hashtag;
	// }


    
}