package com.spring.security.social.login.example.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "symposiumpapers")
public class SymposiumPapers {

	@Column
	private String symposiumPapers;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symposiumid", nullable = false)
	private Symposium stock;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	public Symposium getStock() {
		return stock;
	}

	public void setStock(Symposium stock) {
		this.stock = stock;
	}

	
	public String getSymposiumPapers() {
		return symposiumPapers;
	}

	public void setSymposiumPapers(String symposiumPapers) {
		this.symposiumPapers = symposiumPapers;
	}
	
	
	
	
	
	
	
}
