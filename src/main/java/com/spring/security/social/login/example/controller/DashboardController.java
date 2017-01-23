package com.spring.security.social.login.example.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.security.social.login.example.Enum.FormType;
import com.spring.security.social.login.example.Enum.Sympoconstants;
import com.spring.security.social.login.example.Mongo.SymposiumDynamicFormHandler;
import com.spring.security.social.login.example.database.model.Event;
import com.spring.security.social.login.example.database.model.SymposiumRegistrationFieldsMongo;
import com.spring.security.social.login.example.dto.SocialUser;

@Controller
public class DashboardController {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping(value = "/dash", method = RequestMethod.GET)
	public ModelAndView registrationFieldsDashboard(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userid = (String) request.getSession().getAttribute("userId");
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").in(userid));
		ModelAndView model = new ModelAndView();
		model.setViewName("newdashboard");

		model.addObject("event", mongoTemplate.find(query, Event.class));
		return model;

	}
	
	@RequestMapping(value = "/registrations/{eventId}", method = RequestMethod.GET)
	public ModelAndView registrations(@PathVariable String eventId,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("registrations");
	
		//dynamic forms code
		Query searchUserQuery = new Query(Criteria.where("symposimuId").is(eventId));
		List<SymposiumDynamicFormHandler> find1 = mongoTemplate.find(
				searchUserQuery, SymposiumDynamicFormHandler.class);
		model.addObject("symposium", find1);
		//static form or default form
		Query query=new Query();
		query.addCriteria(Criteria.where("eventId").in(eventId));
		List<SymposiumRegistrationFieldsMongo> find = mongoTemplate.find(query, SymposiumRegistrationFieldsMongo.class);
		model.addObject("isregavailable", !find.isEmpty());
		model.addObject("event",find);
		return model;

	}
	
	
	@RequestMapping(value = { "/select-form-type" }, method = RequestMethod.POST)
	public ModelAndView saveAConference(String eventId,String formType,HttpServletRequest request) {
		
		SocialUser user = (SocialUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Event findById = mongoTemplate.findById(eventId, Event.class);
		
		findById.setFormType(formType);
	
		mongoTemplate.save(findById);
		
		ModelAndView model = new ModelAndView("redirect:/dash");
		
		return model;
	}
	
	
}
