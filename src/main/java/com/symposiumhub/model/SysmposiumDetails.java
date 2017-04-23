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
@Table(name = "symposiumdetails")
public class SysmposiumDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	private String events;

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symposiumid", nullable = false)
	private Symposium symposiumEvent;

	public Symposium getSymposiumEvent() {
		return symposiumEvent;
	}

	public void setSymposiumEvent(Symposium symposiumEvent) {
		this.symposiumEvent = symposiumEvent;
	}

}
