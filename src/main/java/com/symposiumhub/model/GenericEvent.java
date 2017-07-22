package com.symposiumhub.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class GenericEvent extends BaseEvent {

	private String eventid;
	
	private GenericEventRegistrationFields fields;
	
	
	private String userId;
	
	private String imageUrl;
	
	private MultipartFile file;
	
	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventId) {
		this.eventid = eventId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public GenericEventRegistrationFields getFields() {
		return fields;
	}

	public void setFields(GenericEventRegistrationFields fields) {
		this.fields = fields;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
   
	

}
