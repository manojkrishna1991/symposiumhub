package com.spring.security.social.login.example.dto;

import java.io.Serializable;
import java.util.Date;

import com.spring.security.social.login.example.database.model.SymposiumComment;
import com.spring.security.social.login.example.database.model.SymposiumCommentsReply;
import com.spring.security.social.login.example.database.model.User;

public class SymposiumCommentsReplyDto implements Serializable {
	
	private Integer  symposiumReplyId;
	
	private String commentId;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	private String reply;

	private SymposiumComment symposiumComment; 
	

	private User user;
	
	private Date postedDate;
	

	public SymposiumCommentsReplyDto(){
		
	}
	public SymposiumCommentsReplyDto(SymposiumCommentsReply symposiumCommentsReply){
		this.symposiumReplyId=symposiumCommentsReply.getSymposiumReplyId();
		this.reply=symposiumCommentsReply.getReply();
		this.symposiumComment=symposiumCommentsReply.getSymposiumComment();
		this.user=symposiumCommentsReply.getUser();
		this.postedDate=symposiumCommentsReply.getPostedDate();
	}
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
