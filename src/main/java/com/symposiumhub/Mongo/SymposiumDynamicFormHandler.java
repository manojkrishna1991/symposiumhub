package com.symposiumhub.Mongo;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SymposiumDynamicFormHandler {
	
	@Id
	private String id;
	
	private String symposimuId;
	
	private List<SymposiumFieldInfo> symposiumFieldInfo;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymposimuId() {
		return symposimuId;
	}

	public void setSymposimuId(String symposimuId) {
		this.symposimuId = symposimuId;
	}

	public List<SymposiumFieldInfo> getSymposiumFieldInfo() {
		return symposiumFieldInfo;
	}

	public void setSymposiumFieldInfo(List<SymposiumFieldInfo> symposiumFieldInfo) {
		this.symposiumFieldInfo = symposiumFieldInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
