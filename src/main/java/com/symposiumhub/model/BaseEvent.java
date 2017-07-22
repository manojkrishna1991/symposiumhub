package com.symposiumhub.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author manojramana Base event structure
 *
 */
public abstract class BaseEvent {

	

	private String name;		

	private Date dateOfEvent;

	private List<Coordinator> coordinatorsAsList;

	private String regEmail;

	private String content;

	private String eventType;
	
	private String organizationName;
	
	private String department;
	
	private String paymentDetail;
	
	

	public String getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(String paymentDetail) {
		this.paymentDetail = paymentDetail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public List<Coordinator> getCoordinatorsAsList() {
		return coordinatorsAsList;
	}

	public void setCoordinatorsAsList(List<Coordinator> coordinatorsAsList) {
		this.coordinatorsAsList = coordinatorsAsList;
	}

	public String getRegEmail() {
		return regEmail;
	}

	public void setRegEmail(String regEmail) {
		this.regEmail = regEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	

}
