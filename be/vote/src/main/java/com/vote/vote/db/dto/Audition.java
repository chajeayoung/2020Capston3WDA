package com.vote.vote.db.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="audition")
public class Audition {
	
	 	@Id
	    @Column(nullable=false, name="audition_id")
	    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITION_SEQ_GENERATOR")
	    @SequenceGenerator(name="AUDITION_SEQ_GENERATOR", sequenceName="AUDITION_SEQ", allocationSize = 1)
	    private int auditionid;

	    @Column(nullable=true, name="a_title")
	    private String atitle;

	    @Column(nullable=true,name="a_content")
	    private String acontent;
	    
	    @Column(nullable=true,name="a_category")
		private String acategory;
		
		@Column(nullable=true,name="a_username")
	    private String ausername;

//	    @Temporal(TemporalType.TIMESTAMP)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    @Column(nullable=true,name="a_startdate")
	    private Date astartdate;
	    

	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    @Column(nullable=true,name="a_enddate")
	    private Date aenddate;

	    @Column(nullable=true,name="a_recruits")
	    private Integer arecruits;

	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    @Column(nullable=true,name="a_issue")
	    private Date aissue;

	    @Column(nullable=true,name="program_id")
	    private Integer programid;

	    @Column(nullable=true,name="r_id")
	    private Integer rid;

	    @Column(nullable=true,name="a_file")
		private String afile;
		
		@Column(nullable=true,name="p_img")
	    private String pimg;
		

		public int getAuditionid() {
			return auditionid;
		}

		public void setAuditionid(int auditionid) {
			this.auditionid = auditionid;
		}

		public String getAtitle() {
			return atitle;
		}

		public void setAtitle(String atitle) {
			this.atitle = atitle;
		}

		public String getAcontent() {
			return acontent;
		}

		public void setAcontent(String acontent) {
			this.acontent = acontent;
		}

		public String getAcategory() {
			return acategory;
		}

		public void setAcategory(String acategory) {
			this.acategory = acategory;
		}

		public Date getAstartdate() {
			return astartdate;
		}

		public void setAstartdate(Date astartdate) {
			this.astartdate = astartdate;
		}

		public Date getAenddate() {
			return aenddate;
		}

		public void setAenddate(Date aenddate) {
			this.aenddate = aenddate;
		}

		public Integer getArecruits() {
			return arecruits;
		}

		public void setArecruits(Integer arecruits) {
			this.arecruits = arecruits;
		}

		public Date getAissue() {
			return aissue;
		}

		public void setAissue(Date aissue) {
			this.aissue = aissue;
		}

		public Integer getProgramid() {
			return programid;
		}

		public void setProgramid(Integer programid) {
			this.programid = programid;
		}

		public Integer getRid() {
			return rid;
		}

		public void setRid(Integer rid) {
			this.rid = rid;
		}

		
		
		
		
		public String getAfile() {
			return afile;
		}

		public void setAfile(String afile) {
			this.afile = afile;
		}

		public String getPimg() {
			return pimg;
		}

		public void setPimg(String pimg) {
			this.pimg = pimg;
		}

		public String toString() {
			return ""+rid;
		}

		public String getAusername() {
			return ausername;
		}

		public void setAusername(String ausername) {
			this.ausername = ausername;
		}
	    

}