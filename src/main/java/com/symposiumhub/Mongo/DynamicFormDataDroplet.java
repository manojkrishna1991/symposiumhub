package com.spring.security.social.login.example.Mongo;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DynamicFormDataDroplet {

	@Id
	private String id;
		
	private String symposiumID;
	
	private List<Values> values;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymposiumID() {
		return symposiumID;
	}

	public void setSymposiumID(String symposiumID) {
		this.symposiumID = symposiumID;
	}

	public List<Values> getValues() {
		return values;
	}

	public void setValues(List<Values> values) {
		this.values = values;
	}
	
	
	
	
}
