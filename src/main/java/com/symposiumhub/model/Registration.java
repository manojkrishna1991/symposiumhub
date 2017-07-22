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


public class Registration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer  id;
	private String  fullName;
	private String  phoneNumber;
	private String  email;
	private String collegeName;
	private String collegeId;
	private Date registerDate=new Date();
	private Boolean isshow;
	private Boolean isMailSent;
	private Integer eventid;
	private Integer regCount;
	private String sendEmailTo;
	private String name;
	
	
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSendEmailTo() {
		return sendEmailTo;
	}
	public void setSendEmailTo(String sendEmailTo) {
		this.sendEmailTo = sendEmailTo;
	}
	public Integer getRegCount() {
		return regCount;
	}
	public void setRegCount(Integer regCount) {
		this.regCount = regCount;
	}
	public Integer getEventid() {
		return eventid;
	}
	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public String getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	public void setIsshow(Boolean isshow) {
		this.isshow = isshow;
	}

}
