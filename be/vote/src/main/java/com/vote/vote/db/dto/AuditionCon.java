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
@Table(name="audition_con")
public class AuditionCon {
	
	 	@Id
	    @Column(nullable=false, name="form_id")
	 	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUDITION_CON_SEQ_GENERATOR")
	    @SequenceGenerator(name="AUDITION_CON_SEQ_GENERATOR", sequenceName="AUDITION_CON_SEQ", allocationSize = 1)
	    private int formid;

	 	 @Column(nullable=true, name="f_title")
		    private String ftitle;

		    @Column(nullable=true,name="f_profile")
		    private String fprofile;
		    
		    
		    @Column(nullable=true,name="f_addr")
		    private String faddr;
		    
		    
		    @Column(nullable=true,name="f_education")
		    private String feducation;
		    
		    @Column(nullable=true,name="f_gender")
		    private String fgender;
		    
		    @Column(nullable=true,name="f_height")
		    private String fheight;
		    
		    @Column(nullable=true,name="f_weight")
		    private String fweight;
		    
		    @Column(nullable=true,name="f_blood")
		    private String fblood;
		    
		    @Column(nullable=true,name="f_family")
		    private String ffamily;
		    
		    @Column(nullable=true,name="f_hobby")
		    private String fhobby;
		    
		    @Column(nullable=true,name="f_ability")
		    private String fability;
		    
		    @Column(nullable=true,name="f_carrer")
		    private String fcarrer;
		    
		    @Column(nullable=true,name="f_motive")
		    private String fmotive;
		    
		    @Column(nullable=true,name="f_category")
		    private String fcategory;
		    
		    @Column(nullable=true,name="f_introduction")
		    private String fintroduction;
		    
		    @Column(nullable=true,name="f_job")
		    private String fjob;
		    
		    @Column(nullable=true,name="confirm")
		    private String confirm;
		    
		    @Column(nullable=true,name="r_id")
		    private Integer rid;
		    
		    @Column(nullable=true,name="audition_id")
		    private Integer auditionid;

		    @DateTimeFormat(pattern="yyyy-MM-dd")
		    @Column(nullable=true,name="f_date")
		    private Date fdate;
		    
		    @Column(nullable=true,name="f_name")
		    private String fname;
		    
		    
		    @Column(nullable=true,name="f_username")
		    private String fusername;
		    
		    @Column(nullable=true,name="f_userphone")
		    private String fuserphone;
		    
		    @Column(nullable=true,name="f_usermail")
			private String fusermail;
			
			@Column(nullable=true,name="username")
		    private String username;
			
			@Column(nullable=true)
			private String introduce;
			
			@DateTimeFormat(pattern="yyyy-MM-dd")
			@Column(nullable=true)
		    private Date birth;
		    
		    

			public int getFormid() {
				return formid;
			}

			public void setFormid(int formid) {
				this.formid = formid;
			}

			public String getFtitle() {
				return ftitle;
			}

			public void setFtitle(String ftitle) {
				this.ftitle = ftitle;
			}

			public String getFprofile() {
				return fprofile;
			}

			public void setFprofile(String fprofile) {
				this.fprofile = fprofile;
			}

			public String getFaddr() {
				return faddr;
			}

			public void setFaddr(String faddr) {
				this.faddr = faddr;
			}

	

			public String getFeducation() {
				return feducation;
			}

			public void setFeducation(String feducation) {
				this.feducation = feducation;
			}

			public String getFgender() {
				return fgender;
			}

			public void setFgender(String fgender) {
				this.fgender = fgender;
			}

			public String getFheight() {
				return fheight;
			}

			public void setFheight(String fheight) {
				this.fheight = fheight;
			}

			public String getFweight() {
				return fweight;
			}

			public void setFweight(String fweight) {
				this.fweight = fweight;
			}

			public String getFblood() {
				return fblood;
			}

			public void setFblood(String fblood) {
				this.fblood = fblood;
			}

			public String getFfamily() {
				return ffamily;
			}

			public void setFfamily(String ffamily) {
				this.ffamily = ffamily;
			}

			public String getFhobby() {
				return fhobby;
			}

			public void setFhobby(String fhobby) {
				this.fhobby = fhobby;
			}

			public String getFability() {
				return fability;
			}

			public void setFability(String fability) {
				this.fability = fability;
			}

			public String getFcarrer() {
				return fcarrer;
			}

			public void setFcarrer(String fcarrer) {
				this.fcarrer = fcarrer;
			}

			public String getFmotive() {
				return fmotive;
			}

			public void setFmotive(String fmotive) {
				this.fmotive = fmotive;
			}

			public String getFcategory() {
				return fcategory;
			}

			public void setFcategory(String fcategory) {
				this.fcategory = fcategory;
			}

			public String getFintroduction() {
				return fintroduction;
			}

			public void setFintroduction(String fintroduction) {
				this.fintroduction = fintroduction;
			}

			public String getFjob() {
				return fjob;
			}

			public void setFjob(String fjob) {
				this.fjob = fjob;
			}

			public String getConfirm() {
				return confirm;
			}

			public void setConfirm(String confirm) {
				this.confirm = confirm;
			}

			public Integer getRid() {
				return rid;
			}

			public void setRid(Integer rid) {
				this.rid = rid;
			}

			public Integer getAuditionid() {
				return auditionid;
			}

			public void setAuditionid(Integer auditionid) {
				this.auditionid = auditionid;
			}

			public Date getFdate() {
				return fdate;
			}

			public void setFdate(Date fdate) {
				this.fdate = fdate;
			}

			public String getFname() {
				return fname;
			}

			public void setFname(String fname) {
				this.fname = fname;
			}

			public String getFusername() {
				return fusername;
			}

			public void setFusername(String fusername) {
				this.fusername = fusername;
			}

			public String getFuserphone() {
				return fuserphone;
			}

			public void setFuserphone(String fuserphone) {
				this.fuserphone = fuserphone;
			}

			public String getFusermail() {
				return fusermail;
			}

			public void setFusermail(String fusermail) {
				this.fusermail = fusermail;
			}
		    
			

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getIntroduce() {
				return introduce;
			}

			public void setIntroduce(String introduce) {
				this.introduce = introduce;
			}

			public String getBirth() {

				String a = birth.toString() .split(" ")[0];

				// 2020-07-25 00000
				// List[ 2020-07-25, 00000]

				return a;
			}

			public Date getBirth2() {

				return this.birth;
			}

			public void setBirth(Date birth) {
				this.birth = birth;
			}

	


			@Override
			public String toString() {
				return "AuditionCon [auditionid=" + auditionid + ", confirm=" + confirm + ", fability=" + fability
						+ ", faddr=" + faddr + ", fblood=" + fblood + ", fcarrer=" + fcarrer + ", fcategory="
						+ fcategory + ", fdate=" + fdate + ", feducation=" + feducation + ", ffamily=" + ffamily
						+ ", fgender=" + fgender + ", fheight=" + fheight + ", fhobby=" + fhobby + ", fintroduction="
						+ fintroduction + ", fjob=" + fjob + ", fmotive=" + fmotive + ", fname=" + fname + ", formid="
						+ formid + ", fprofile=" + fprofile + ", ftitle=" + ftitle + ", fusermail=" + fusermail
						+ ", fusername=" + fusername + ", fuserphone=" + fuserphone + ", fweight=" + fweight + ", rid="
						+ rid + ", username=" + username +", introduce=" + introduce +", birth=" + birth + "]";
			}
		    
		    
	
}
