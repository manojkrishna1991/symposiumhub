package com.spring.security.social.login.example.service;


import java.util.List;

import com.spring.security.social.login.example.database.model.Coordinator;
import com.spring.security.social.login.example.database.model.Event;
import com.spring.security.social.login.example.database.model.Papers;
/*import com.spring.security.social.login.example.database.model.ImageUrl;
*/import com.spring.security.social.login.example.database.model.RegisterForASymposium;
import com.spring.security.social.login.example.database.model.Subscribe;
import com.spring.security.social.login.example.database.model.Symposium;
import com.spring.security.social.login.example.database.model.SymposiumComment;
import com.spring.security.social.login.example.database.model.SymposiumCommentsReply;
import com.spring.security.social.login.example.database.model.SymposiumRegistrationFields;
import com.spring.security.social.login.example.dto.SymposiumCommentDto;
import com.spring.security.social.login.example.dto.SymposiumDto;

/**
 * 
 * @author manoj
 *
 */
public interface SymposiumServiceInterface {

	public Symposium SaveNewSymposium(Symposium symposium);

	public RegisterForASymposium regsiterForSymposium(
			RegisterForASymposium symposium);
	

	public SymposiumDto getSymposium(String id);

	public List<Symposium> getSymposiumByUserId(String userID);

	public List<Symposium> getSymposiumsBySymposiumId(String userId,
			String symposiumId);

	public List<Symposium> getSymposiumName(String userId);

	public Symposium updateSymposium(Symposium symposium);

	public List<Symposium> getSymposium();
	
	public void saveSubcribtion(Subscribe subscribe);

	void saveCordinator(Coordinator coordinator);

	void saveREgisterFields(SymposiumRegistrationFields sFields);
	
	public List<RegisterForASymposium> getSymposiumRegistrations(String symposiumId) ;
	
	public List<Object[]> getValidSymposiumRegistrations();
	
	public void updateStatusToIsMailSent(List<String> list) ;
	
	public List<Symposium> getSymposiumByLimit();

	
	public void savePapers(Papers paper);
	
	public void saveSymposiumComments(SymposiumComment symposiumComment);
	
	public SymposiumCommentDto getSymposiumComments(String commentId);
	
	public void saveSymposiumCommentsReply(SymposiumCommentsReply symposiumCommentsReply);

	
}
