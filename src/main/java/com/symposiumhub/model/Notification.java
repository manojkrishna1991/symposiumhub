package com.symposiumhub.model;

public class Notification {
	
	private Integer id;
	
	
	private String notification;
	
	
	private String type;
	
	
	private Integer toprofileid;
	
	private Integer fromprofileid;
	
	
	


	

	public Integer getToprofileid() {
		return toprofileid;
	}


	public void setToprofileid(Integer toprofileid) {
		this.toprofileid = toprofileid;
	}


	public Integer getFromprofileid() {
		return fromprofileid;
	}


	public void setFromprofileid(Integer fromprofileid) {
		this.fromprofileid = fromprofileid;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNotification() {
		return notification;
	}


	public void setNotification(String notification) {
		this.notification = notification;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	

}
