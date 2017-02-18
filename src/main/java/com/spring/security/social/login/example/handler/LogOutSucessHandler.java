package com.spring.security.social.login.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.security.social.login.example.service.ActiveUserStore;
import com.spring.security.social.login.example.service.LoggedUser;

@Component
public class LogOutSucessHandler extends SimpleUrlLogoutSuccessHandler {
	
	@Autowired
    ActiveUserStore activeUserStore;
	
	  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	            throws IOException, ServletException {
		  
		  HttpSession session = request.getSession(false);
	        if (session != null) {
	        	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	            LoggedUser user = new LoggedUser(userDetails, activeUserStore);
	            session.setAttribute("loggedUser", user);
	        }
		  
		  
	        super.handle(request, response, authentication);
	    }

}
