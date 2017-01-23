package com.spring.security.social.login.example.database.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name="symposiumregistrationfields")
public class SymposiumRegistrationFields implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	@Column
	private Boolean  fullName;
	@Column
	private Boolean  phoneNumber;
	@Column
	private Boolean  email;
	@Column
	private Boolean collegeName;
	@Column
	private Boolean collegeId;
	@Column
	private Boolean registerDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symposiumid")
	private Symposium symposium; 
	
	public Integer getId() {
		return id;
	}
	public Symposium getSymposium() {
		return symposium;
	}
	public void setSymposium(Symposium symposium) {
		this.symposium = symposium;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getFullName() {
		return fullName;
	}
	public void setFullName(Boolean fullName) {
		this.fullName = fullName;
	}
	public Boolean getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Boolean phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Boolean getEmail() {
		return email;
	}
	public void setEmail(Boolean email) {
		this.email = email;
	}
	public Boolean getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(Boolean collegeName) {
		this.collegeName = collegeName;
	}
	public Boolean getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(Boolean collegeId) {
		this.collegeId = collegeId;
	}
	public Boolean getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Boolean registerDate) {
		this.registerDate = registerDate;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public boolean isValid(){
		if(this.fullName||this.collegeName||this.email||this.phoneNumber||this.collegeId ){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
