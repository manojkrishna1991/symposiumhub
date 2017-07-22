package com.symposiumhub.model;

public class GenericEventRegistrationFields {
	
	private String id;
	
	private Boolean fullName;
	
	private Boolean phoneNumber;
	
	private Boolean email;
	
	private Boolean collegeName;
	
	private Boolean collegeId;
		
	private String eventId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
}
