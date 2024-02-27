package com.symposiumhub.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.Enum.FormType;
import com.symposiumhub.Enum.Sympoconstants;
import com.symposiumhub.Mongo.SymposiumDynamicFormHandler;
import com.symposiumhub.datasource.EventRepositoryComponent;
import com.symposiumhub.datasource.RegisterForEventComponent;
import com.symposiumhub.dto.SocialUser;
import com.symposiumhub.model.Event;
import com.symposiumhub.model.GenericEvent;
import com.symposiumhub.model.RegisterForASymposium;
import com.symposiumhub.model.Registration;
import com.symposiumhub.model.SymposiumRegistrationFieldsMongo;

@Controller
public class DashboardController {
	
	
	
	@Autowired
	private EventRepositoryComponent eventRepository;
	@Autowired
	private RegisterForEventComponent registerForEventComponet;

	@GetMapping(value = "/dashboard")
	public ModelAndView registrationFieldsDashboard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model = new ModelAndView();
		model.setViewName("newdashboard");
        List <GenericEvent> genericEvent=eventRepository.getEventByUserId(user.getUserId());
		model.addObject("event",genericEvent);
		return model;

	}
	
	
	
	
	
	
/*	
	@RequestMapping(value = { "/select-form-type" }, method = RequestMethod.POST)
	public ModelAndView saveAConference(String eventId,String formType,HttpServletRequest request) {
		
		SocialUser user = (SocialUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Event findById = mongoTemplate.findById(eventId, Event.class);
		
		findById.setFormType(formType);
	
		mongoTemplate.save(findById);
		
		ModelAndView model = new ModelAndView("redirect:/dash");
		
		return model;
	}*/
	
	
}
