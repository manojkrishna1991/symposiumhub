package com.symposiumhub.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class DashBoardData {
	

	
	private String editUrl;
	
	private String viewUrl;
	
	private String viewRegistrations;



	public String getEditUrl() {
		return editUrl;
	}

	public void setEditUrl(String editUrl) {
		this.editUrl = editUrl;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getViewRegistrations() {
		return viewRegistrations;
	}

	public void setViewRegistrations(String viewRegistrations) {
		this.viewRegistrations = viewRegistrations;
	}

	
	
	

}
