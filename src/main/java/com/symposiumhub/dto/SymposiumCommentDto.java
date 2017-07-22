package com.symposiumhub.dto;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.symposiumhub.model.Symposium;
import com.symposiumhub.model.SymposiumComment;
import com.symposiumhub.model.SymposiumCommentsReply;
import com.symposiumhub.model.User;


public class SymposiumCommentDto{
	   
	
	
	public SymposiumCommentDto(SymposiumComment symposiumComment){
		
		this.id=symposiumComment.getId();
		this.comment=symposiumComment.getComment();
		this.user=new UserDto(symposiumComment.getUser());
		this.postedDate=symposiumComment.getPostedDate();
		Symposium symposium = symposiumComment.getSymposium();
		this.symposium=symposium.getName();
		this.symposiumId=symposium.getSymposiumid();
		ArrayList<SymposiumCommentsReplyDto> arrayList = new ArrayList<SymposiumCommentsReplyDto>();
		
		for (SymposiumCommentsReply symposiumCommentsReply : symposiumComment.getSymposiumCommentsReplies()) {
			arrayList.add(new SymposiumCommentsReplyDto(symposiumCommentsReply));
		} 
		
		this.reply=arrayList;
	}

	
	private Integer  id;
	
	
	private String comment;
	

	private String symposium;
	
	
	private String symposiumId;
	
	
	
	public String getSymposiumId() {
		return symposiumId;
	}

	public void setSymposiumId(String symposiumId) {
		this.symposiumId = symposiumId;
	}

	public String getSymposium() {
		return symposium;
	}

	public void setSymposium(String symposium) {
		this.symposium = symposium;
	}


	private UserDto user;
	
	private Date postedDate;
	
	private List<SymposiumCommentsReplyDto> reply;


	public List<SymposiumCommentsReplyDto> getReply() {
		return reply;
	}

	public void setReply(List<SymposiumCommentsReplyDto> reply) {
		this.reply = reply;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	

}
