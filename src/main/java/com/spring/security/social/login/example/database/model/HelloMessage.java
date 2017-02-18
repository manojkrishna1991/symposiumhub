package com.spring.security.social.login.example.database.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Chat")
public class HelloMessage {
	
	@Id
	private String id=UUID.randomUUID().toString();
	
	
	private String name;

	private String roomName;
	
	private String userName;
	
	private Date createdDate=new Date();
	
	
	
	

	public Date getCreatedDate() {
		return createdDate;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
