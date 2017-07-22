 package com.symposiumhub.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.WebUtils;

import com.symposiumhub.datasource.NotificationComponent;
import com.symposiumhub.datasource.ProfileComponent;
import com.symposiumhub.model.Profile;
import com.symposiumhub.model.User;
import com.symposiumhub.service.ActiveUserStore;
import com.symposiumhub.service.LoggedUser;
import com.symposiumhub.service.UserService;

/**
 * Servlet Filter implementation class UrlRedirectionFilter
 */
@Component("UrlRedirectionFilter")
public class UrlRedirectionFilter implements Filter {

	private static final Log logger = LogFactory.getLog(UrlRedirectionFilter.class);

	@Autowired
	private ProfileComponent profile;

	@Autowired
	ActiveUserStore activeUserStore;

	@Autowired
	NotificationComponent notificationComponent;

	@Autowired
	UserService userService;

	private User userById;

	/**
	 * Default constructor.
	 */
	public UrlRedirectionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		UserDetails user = null;
		try {
			user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		if (user != null) {
			List<Profile> currentProfile = profile.getProfile(user.getUserId());
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			if (CollectionUtils.isEmpty(currentProfile) && !httpServletRequest.getRequestURI().equals("/profile")) {
				HttpServletResponse httprepsonse=	(HttpServletResponse)response;
				httprepsonse.sendRedirect(
								"/profile?message=error&messagetext=please complete the  profile to proceed")
						;
			}
			
			HttpSession session = httpServletRequest.getSession(false);
			if (session != null) {
				UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();

				userById = userService.getUserById(userDetails.getUserId());

				LoggedUser sessionAttribute = (LoggedUser) WebUtils.getSessionAttribute(httpServletRequest,
						"loggedUser");

				if (sessionAttribute == null) {
					List<Profile> profile = this.profile.getProfile(userDetails.getUserId());
					if (!CollectionUtils.isEmpty(currentProfile) ) {
						
						WebUtils.setSessionAttribute(httpServletRequest,"profileObject", currentProfile.get(0)); 
						
						
						//this is setting profile pic in user object
						String photo = currentProfile.get(0).getPhoto();
						userById.setImageUrl(photo==null ? "/resources/assets/images/wireframe/image.png":photo);
					}
					LoggedUser loggedUser = new LoggedUser(userById, activeUserStore);
					session.setAttribute("loggedUser", loggedUser);
					// setting the notfication count
					
					if (!profile.isEmpty()) {
						httpServletRequest.getSession().setAttribute("notification",
								notificationComponent.getNotificationCount(profile.get(0).getId()));
					}
				}
			}
			
			
			

		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
