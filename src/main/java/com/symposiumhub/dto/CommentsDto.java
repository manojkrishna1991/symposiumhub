package com.symposiumhub.dto;

public class CommentsDto {

	private String symposiumId;
	
	private String comment;
	
	private String userId;

	public String getSymposiumId() {
		return symposiumId;
	}

	public void setSymposiumId(String symposiumId) {
		this.symposiumId = symposiumId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
