package com.vote.vote.db.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="audition_result")
public class AuditionResult {
	
	 	@Id
	    @Column(nullable=false, name="result_id")
	    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITION_RESULT_SEQ_GENERATOR")
	    @SequenceGenerator(name="AUDITION_RESULT_SEQ_GENERATOR", sequenceName="AUDITION_RESULT_SEQ", allocationSize = 1)
	    private int resultid;

	    @Column(nullable=true, name="r_title")
	    private String rtitle;
	    
	    @Column(nullable=true, name="r_content")
	    private String rcontent;
	    
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    @Column(nullable=true,name="r_date")
	    private Date rdate;
	    
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    @Column(nullable=true,name="r_mdate")
	    private Date rmdate;
	    
	    
	    @Column(nullable=true, name="program_id")
	    private Integer programid;
	    
	    
	    @Column(nullable=true, name="r_id")
		private Integer rid;
		
		@Column(nullable=true, name="r_file")
	    private String rfile;

		@Column(nullable=true, name="r_username")
		private String rusername;
		
		@Column(nullable=true, name="audition_id")
		private int auditionid;

		@Column(nullable=true,name="r_img")
	    private String rimg;

		public int getResultid() {
			return resultid;
		}


		public void setResultid(int resultid) {
			this.resultid = resultid;
		}


		public String getRtitle() {
			return rtitle;
		}


		public void setRtitle(String rtitle) {
			this.rtitle = rtitle;
		}


		public String getRcontent() {
			return rcontent;
		}


		public void setRcontent(String rcontent) {
			this.rcontent = rcontent;
		}


		public Date getRdate() {
			return rdate;
		}


		public void setRdate(Date rdate) {
			this.rdate = rdate;
		}


		public Date getRmdate() {
			return rmdate;
		}


		public void setRmdate(Date rmdate) {
			this.rmdate = rmdate;
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

		public String getRfile() {
			return rfile;
		}

		public void setRfile(String rfile) {
			this.rfile = rfile;
		}

		public String getRusername() {
			return rusername;
		}

		public void setRusername(String rusername) {
			this.rusername = rusername;
		}

		public int getAuditionid() {
			return auditionid;
		}

		public void setAuditionid(int auditionid) {
			this.auditionid = auditionid;
		}

		public String getRimg() {
			return rimg;
		}

		public void setRimg(String rimg) {
			this.rimg = rimg;
		}
		

		
		public AuditionResult(){}


		@Override
		public String toString() {
			return "AuditionResult [auditionid=" + auditionid + ", programid=" + programid + ", rcontent=" + rcontent
					+ ", rdate=" + rdate + ", resultid=" + resultid + ", rfile=" + rfile + ", rid=" + rid + ", rmdate="
					+ rmdate + ", rtitle=" + rtitle + ", rusername=" + rusername + "]";
		}
	    
	    
	    
}