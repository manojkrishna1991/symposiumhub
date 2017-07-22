package com.symposiumhub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="regsiterforsymposium")
public class RegisterForASymposium implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	@Column
	private String  fullName;
	@Column
	private String  phoneNumber;
	@Column
	private String  email;
	@Column
	private String collegeName;
	@Column
	private String collegeId;
	@Column
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date registerDate=new Date();
	@Column
	private Boolean isshow;
	@Column
	private Boolean isMailSent;
	

	public Boolean getIsMailSent() {
		return isMailSent;
	}
	public void setIsMailSent(Boolean isMailSent) {
		this.isMailSent = isMailSent;
	}
	public Boolean getIsshow() {
		return isshow;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symposiumid")
	private Symposium symposium; 
	
	@Transient
	private String symposiumIddoto;
	
	
	
	public Boolean isIsshow() {
		return isshow;
	}
	public void setIsshow(Boolean isshow) {
		this.isshow = isshow;
	}
	public String getSymposiumIddoto() {
		return symposiumIddoto;
	}
	public void setSymposiumIddoto(String symposiumIddoto) {
		this.symposiumIddoto = symposiumIddoto;
	}
	public Symposium getSymposium() {
		return symposium;
	}
	public void setSymposium(Symposium symposium) {
		this.symposium = symposium;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	
	
	

}
