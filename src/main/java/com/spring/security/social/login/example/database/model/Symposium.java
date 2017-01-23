package com.spring.security.social.login.example.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author manoj
 *
 */

@Entity
@Table(name = "symposium")
public class Symposium implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String symposiumid=UUID.randomUUID().toString();


	public String getSymposiumid() {
		return symposiumid;
	}

	public void setSymposiumid(String symposiumid) {
		this.symposiumid = symposiumid;
	}

	@Column
	private String name;

	@Column
	private String collegeName;

	@Column
	private String department;

	@Column
	private String country;

	@Column
	private Date dateOfEvent;

	@Column
	private String webAddress;

	@Column
	private String address;

	@Column
	private String impDescription;

	@Column
	private Date fromDate;

	@Column
	private Date toDate;

	@Column
	private String regEmail;

	@Column
	private String excludeReg;

	@Column
	private String events;
	
	@Column
	private String symposiumPapers;
	
	@Column
	private String userId;
	
	@Column
	private Date createdDate=new Date();

	@Column(name="ImageUrl")
	private String imageUrl;
	
	@Column
	private String paymentType;
	
	

	@Column
	private String paymentDetail;
	
	
	@Column
	private String phoneNo;
	
	@Column
	private String emailId;
	
	@Column
	private String mobileNo;
	
	@Column
	private String compressedPath;
	
	
	
	public String getCompressedPath() {
		return compressedPath;
	}

	public void setCompressedPath(String compressedPath) {
		this.compressedPath = compressedPath;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(String paymentDetail) {
		this.paymentDetail = paymentDetail;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "symposium")
	private SymposiumRegistrationFields fields;
	
	
	
	public SymposiumRegistrationFields getFields() {
		return fields;
	}

	public void setFields(SymposiumRegistrationFields fields) {
		this.fields = fields;
	}

	@Transient
	private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "symposium")
	private List<RegisterForASymposium> registerForSymposium;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "symposium")
	@Fetch (FetchMode.SELECT) 
	private List<Coordinator> coordinators;
	
	
	@Transient
	private List<Coordinator> coordinatorsAsList;

	


	public List<Coordinator> getCoordinatorsAsList() {
		return coordinatorsAsList;
	}

	public void setCoordinatorsAsList(List<Coordinator> coordinatorsAsList) {
		this.coordinatorsAsList = coordinatorsAsList;
	}
   
	
	public List<Coordinator> getCoordinators() {
		return coordinators;
	}

	public void setCoordinators(List<Coordinator> coordinators) {
		this.coordinators = coordinators;
	}
	
	

	public List<RegisterForASymposium> getRegisterForSymposium() {
		return registerForSymposium;
	}

	public void setRegisterForSymposium(
			List<RegisterForASymposium> registerForSymposium) {
		this.registerForSymposium = registerForSymposium;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getSymposiumPapers() {
		return symposiumPapers;
	}

	public void setSymposiumPapers(String symposiumPapers) {
		this.symposiumPapers = symposiumPapers;
	}

	public String getExcludeReg() {
		return excludeReg;
	}

	public void setExcludeReg(String excludeReg) {
		this.excludeReg = excludeReg;
	}

	public String getRegEmail() {
		return regEmail;
	}

	public void setRegEmail(String regEmail) {
		this.regEmail = regEmail;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImpDescription() {
		return impDescription;
	}

	public void setImpDescription(String impDescription) {
		this.impDescription = impDescription;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
    
	
	
}
