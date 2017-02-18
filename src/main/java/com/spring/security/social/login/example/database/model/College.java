package com.spring.security.social.login.example.database.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class College implements Serializable {
	private static final long serialVersionUID = 1L;

	

	
	private Integer id;
	

	private String name;
	

	private Date createdDate;
	
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	private String compressedPath;
	
	
	private String compressedPathOne;
	
	private Integer rating;
	
	
	
	
	public String getCompressedPathOne() {
		return compressedPathOne;
	}

	public void setCompressedPathOne(String compressedPathOne) {
		this.compressedPathOne = compressedPathOne;
	}

	public String getCompressedPath() {
		return compressedPath;
	}

	public void setCompressedPath(String compressedPath) {
		this.compressedPath = compressedPath;
	}

	private String location;
	
	private String url;
	
	private String image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	

}
