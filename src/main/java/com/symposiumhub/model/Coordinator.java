package com.spring.security.social.login.example.database.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name="coordinator")
public class Coordinator implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Symposium getSymposium() {
		return symposium;
	}

	public void setSymposium(Symposium symposium) {
		this.symposium = symposium;
	}


	@Column
	private String name;
	
	@Column
	private String emailid;
	
	@Column
	private String phoneno;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symposiumid")
	private Symposium symposium; 
	
	
	
	public boolean isValid(){
		if(StringUtils.isEmpty(this.emailid) || StringUtils.isEmpty(this.name) || StringUtils.isEmpty(this.phoneno)){
			return false;
		}
		return true;
	}
	

}
