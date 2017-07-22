package com.symposiumhub.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.datasource.NotificationComponent;
import com.symposiumhub.datasource.ProfileComponent;
import com.symposiumhub.model.Notification;
import com.symposiumhub.model.Profile;

@Controller
public class NotificationController {

	@Autowired
	private NotificationComponent notificationComponent;

	@Autowired
	private ProfileComponent profileComponent;

	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public ModelAndView review(Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ModelAndView modelAndView = new ModelAndView();

		List<Notification> notification = notificationComponent.getNotification();

		

		model.addAttribute("notifications", notification);

		// setting the notfication count
		List<Profile> profile = profileComponent.getProfile(user.getUserId());
		
		

		if (!profile.isEmpty()) {
			asyncronousNotificationUpdate(profile.get(0).getId());
			request.getSession().setAttribute("notification",
					notificationComponent.getNotificationCount(profile.get(0).getId()));
		}
		modelAndView.setViewName("notification");

		return modelAndView;
	}


	public void asyncronousNotificationUpdate(Integer id) {
		
			notificationComponent.update(id);

	}

}
