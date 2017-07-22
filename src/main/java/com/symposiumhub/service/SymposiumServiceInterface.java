package com.symposiumhub.service;


import java.util.List;

import com.symposiumhub.dto.SymposiumCommentDto;
import com.symposiumhub.dto.SymposiumDto;
import com.symposiumhub.model.Coordinator;
import com.symposiumhub.model.Event;
import com.symposiumhub.model.Papers;
import com.symposiumhub.model.RegisterForASymposium;
import com.symposiumhub.model.Subscribe;
import com.symposiumhub.model.Symposium;
import com.symposiumhub.model.SymposiumComment;
import com.symposiumhub.model.SymposiumCommentsReply;
import com.symposiumhub.model.SymposiumRegistrationFields;

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
