package com.spring.security.social.login.example.database.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.spring.security.social.login.example.dto.SymposiumCommentDto;

@Entity
@Table(name="symposiumcomment")
public class SymposiumComment implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	
	
	
	
	public SymposiumComment(SymposiumCommentDto symcom) {
		// TODO Auto-generated constructor stub
		this.id=symcom.getId();
		this.comment=symcom.getComment();
	}

	public SymposiumComment() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	private String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symposiumid")
	private Symposium symposium;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="symposiumComment")
	private List<SymposiumCommentsReply> symposiumCommentsReplies;
	
	public List<SymposiumCommentsReply> getSymposiumCommentsReplies() {
		return symposiumCommentsReplies;
	}

	public void setSymposiumCommentsReplies(List<SymposiumCommentsReply> symposiumCommentsReplies) {
		this.symposiumCommentsReplies = symposiumCommentsReplies;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;
	
	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	private Date postedDate;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Symposium getSymposium() {
		return symposium;
	}

	public void setSymposium(Symposium symposium) {
		this.symposium = symposium;
	} 

}
