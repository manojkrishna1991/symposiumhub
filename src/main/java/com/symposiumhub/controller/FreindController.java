package com.symposiumhub.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.datasource.ProfileComponent;
import com.symposiumhub.model.Profile;

@Controller
public class FreindController {

	@Autowired
	private ProfileComponent profile;

	@GetMapping(value = "/addfriends")
	public ModelAndView review(Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView modelAndView = new ModelAndView();

		List<Profile> currentProfile = profile.getProfile(user.getUserId());
		
		List<Profile> profileToMakeFreinds=null;	
		if(!currentProfile.isEmpty()){
			profileToMakeFreinds= profile.getProfileToMakeFreinds(user.getUserId(),
					currentProfile.get(0).getId());
		}else{
			profileToMakeFreinds=Collections.EMPTY_LIST;
		}
		model.addAttribute("profiles", profileToMakeFreinds);
		modelAndView.setViewName("addfreinds");
		return modelAndView;
	}

	@GetMapping(value = "/addfriends/{profileid}")
	public ModelAndView addfreinds(@PathVariable Integer profileid, Model model, HttpServletRequest request) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		List<Profile> currentProfile = profile.getProfile(user.getUserId());
		profile.insertFreinds(currentProfile.get(0).getId(), profileid);
		List<Profile>  c = profile.getProfileByProfileId(profileid);
		String messagetext= c .get(0).getName()+"was added ";
		modelAndView.setViewName("redirect:/addfriends");
		return modelAndView;
	}
	
	@GetMapping(value = "/myfriends")
	public ModelAndView myfreinds(Model model, HttpServletRequest request) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		List<Profile> currentProfile = profile.getProfile(user.getUserId());
		if(!currentProfile.isEmpty()){
		model.addAttribute("profiles", profile.getMyFreinds(currentProfile.get(0).getId()));
		}else{
			model.addAttribute("profiles", Collections.EMPTY_LIST);
		}
		modelAndView.setViewName("myfreinds");
		return modelAndView;
	}
	
	
	@GetMapping(value = "/friendrequest")
	public ModelAndView freindrequest(Model model, HttpServletRequest request) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		List<Profile> currentProfile = profile.getProfile(user.getUserId());
		if(!currentProfile.isEmpty()){
		model.addAttribute("profiles", profile.getRequestedFreinds(currentProfile.get(0).getId()));
		}else{
			model.addAttribute("profiles", Collections.EMPTY_LIST);
		}
		modelAndView.setViewName("freindsrequested");
		return modelAndView;
	}
	
	
	@GetMapping(value = "/friendrequestaccept/{profileId}")
	public ModelAndView freindrequestaccept(@PathVariable Integer profileId, Model model, HttpServletRequest request) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		List<Profile> currentProfile = profile.getProfile(user.getUserId());
		profile.updateFreinds(currentProfile.get(0).getId(), profileId);
		List<Profile>  c = profile.getProfileByProfileId(profileId);
		String messagetext= c .get(0).getName()+" "+"was added ";
		modelAndView.setViewName("redirect:/myfriends?message=success&messagetext="+messagetext+"");
		return modelAndView;
	}
	
	
	@GetMapping(value = "/postmessage")
	public ModelAndView postmessage(Model model, HttpServletRequest request) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		List<Profile> currentProfile = profile.getProfile(user.getUserId());
		modelAndView.setViewName("postmessage");
		return modelAndView;
	}
	
	

	@PostMapping(value = "/postmessage")
	public ModelAndView postmessage(String message,Model model, HttpServletRequest request) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView modelAndView = new ModelAndView();
		List<Profile> currentProfile = profile.getProfile(user.getUserId());
		profile.insertActivity(message, currentProfile.get(0).getId());
		modelAndView.setViewName("redirect:/messages");
		return modelAndView;
	}
	
	
	
	
	
	
	
	

}
