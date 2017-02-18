package com.spring.security.social.login.example.database.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name="symposiumcommentsreply")
public class SymposiumCommentsReply implements Serializable {

	public Integer getSymposiumReplyId() {
		return symposiumReplyId;
	}

	public void setSymposiumReplyId(Integer symposiumReplyId) {
		this.symposiumReplyId = symposiumReplyId;
	}

	public SymposiumComment getSymposiumComment() {
		return symposiumComment;
	}

	public void setSymposiumComment(SymposiumComment symposiumComment) {
		this.symposiumComment = symposiumComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  symposiumReplyId;
	
	@Column
	private String reply;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symposiumcomments")
	private SymposiumComment symposiumComment; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	private Date postedDate;
	
	

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
