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
@Table(name="popular")
public class Popular {
    @Id
    @Column(name="popular_id", nullable=false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POPULAR_SEQ_GENERATOR")
    @SequenceGenerator(name="POPULAR_SEQ_GENERATOR", sequenceName="POPULAR_SEQ", allocationSize = 1)
    private int id;

    @Column(name="p_name", nullable=false) //인기인 이름
    private String name;

    @Column(name="p_image", nullable=false) //인기인 이미지
	private String img;
	
	@Column(name="logo", nullable=false) //인기인 이미지
    private String logo;


    @Column(name="program_id", nullable=false)
    private int pid;
    

	@DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="BIRTH", nullable=false) //인기인 이미지
	private Date birth;
	
    @Column(name="WEIGHT", nullable=false)
	private String weight;

    @Column(name="HEIGHT", nullable=false)
	private String height;
	
	@Column(name="BLOOD", nullable=false)
	private String blood;
	
	@Column(name="HOBBY", nullable=false)
	private String hobby;

	@Column(name="ABILITY", nullable=false)
	private String ability;
	
	@Column(name="INTRODUCE", nullable=false)
	private String intro;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getAbility() {
		return ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}


	@Override
	public String toString() {
		return "Popular [ability=" + ability + ", birth=" + birth + ", blood=" + blood + ", height=" + height
				+ ", hobby=" + hobby + ", id=" + id + ", img=" + img + ", intro=" + intro + ", logo=" + logo + ", name="
				+ name + ", pid=" + pid + ", weight=" + weight + "]";
	}

	


	
	
    
}