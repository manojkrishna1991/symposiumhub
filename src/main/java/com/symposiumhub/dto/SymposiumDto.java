package com.symposiumhub.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.symposiumhub.model.Coordinator;
import com.symposiumhub.model.Papers;
import com.symposiumhub.model.RegisterForASymposium;
import com.symposiumhub.model.Symposium;
import com.symposiumhub.model.SymposiumComment;
import com.symposiumhub.model.SymposiumRegistrationFields;

/**
 * 
 * @author manoj
 *
 */


public class SymposiumDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String symposiumid;


	
	private String name;

	
	private String collegeName;

	
	private String department;

	
	private String country;

	
	private Date dateOfEvent;

	
	private String webAddress;

	
	private String address;

	
	private String impDescription;

	
	private Date fromDate;

	private Date toDate;

	private String regEmail;

	private String excludeReg;

	private String events;
	
	private String symposiumPapers;
	
	private String userId;
	
	private Date createdDate=new Date();

	private String imageUrl;
	
	private String paymentType;
	
	

	private String paymentDetail;
	
	
	private String phoneNo;
	
	private String emailId;
	
	private String mobileNo;
	
	private String compressedPath;
	
	private List<Papers> papers;
	
	private List<SymposiumCommentDto> symposiumComment=new ArrayList<>();
	
	private MultipartFile file;

	private List<RegisterForASymposium> registerForSymposium;

	private List<Coordinator> coordinators;
	
	
	private List<Coordinator> coordinatorsAsList;
	
	
	
	public SymposiumDto(Symposium symposium) {
		// TODO Auto-generated constructor stub
		
		
		this. symposiumid = symposium.getSymposiumid() ;


		
		this. name= symposium.getName();

		
		this. collegeName= symposium.getCollegeName();

		
		this. department= symposium.getDepartment();

		
		this. country= symposium.getCountry();

		
		this.dateOfEvent= symposium.getDateOfEvent();

		
		this. webAddress= symposium.getWebAddress();

		
		this. address= symposium.getAddress();

		
		this. impDescription= symposium.getImpDescription();

		
		this.fromDate= symposium.getFromDate();

		this.toDate= symposium.getToDate();

		this. regEmail= symposium.getRegEmail();

		this. excludeReg= symposium.getExcludeReg();

		this. events= symposium.getEvents();
		
		this. symposiumPapers= symposium.getSymposiumPapers();
		
		this. userId= symposium.getUserId();
		
		this.createdDate=symposium.getCreatedDate();

		this. imageUrl= symposium.getImageUrl();
		
		this. paymentType= symposium.getPaymentType();
		
		

		this. paymentDetail= symposium.getPaymentDetail();
		
		
		this. phoneNo= symposium.getPhoneNo();
		
		this. emailId= symposium.getEmailId();
		
		this. mobileNo= symposium.getMobileNo();
		
		this. compressedPath= symposium.getCompressedPath();
		
		this. papers= symposium.getPapers();
		
		
		this.file= symposium.getFile();

		this. registerForSymposium= symposium.getRegisterForSymposium();

		this.coordinators= symposium.getCoordinators();
		
		
		this.coordinatorsAsList= symposium.getCoordinatorsAsList();
		
		for (SymposiumComment symcom : symposium.getSymposiumComment()) {
			
			this.symposiumComment.add(new SymposiumCommentDto(symcom));
		}
		
		
		
		
		
	}
	
	

	public List<SymposiumCommentDto> getSymposiumComment() {
		return symposiumComment;
	}



	public void setSymposiumComment(List<SymposiumCommentDto> symposiumComment) {
		this.symposiumComment = symposiumComment;
	}



	public String getSymposiumid() {
		return symposiumid;
	}

	public void setSymposiumid(String symposiumid) {
		this.symposiumid = symposiumid;
	}

	

	

	public List<Papers> getPapers() {
		return papers;
	}

	public void setPapers(List<Papers> papers) {
		this.papers = papers;
	}

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

	private SymposiumRegistrationFields fields;
	
	
	
	public SymposiumRegistrationFields getFields() {
		return fields;
	}

	public void setFields(SymposiumRegistrationFields fields) {
		this.fields = fields;
	}

	
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
