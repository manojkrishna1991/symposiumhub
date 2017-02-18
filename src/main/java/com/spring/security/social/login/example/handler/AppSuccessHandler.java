package com.spring.security.social.login.example.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.security.social.login.example.database.model.Profile;
import com.spring.security.social.login.example.datasource.NotificationComponent;
import com.spring.security.social.login.example.datasource.ProfileComponent;
import com.spring.security.social.login.example.service.ActiveUserStore;
import com.spring.security.social.login.example.service.LoggedUser;
import com.spring.security.social.login.example.util.SecurityUtil;

/**
 * @author <a href="mailto:psunil1278@gmail.com">Sunil Kumar</a>
 * @since 29/12/15
 */
@Component
public class AppSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
    @Autowired
    ActiveUserStore activeUserStore;
    
    @Autowired
    NotificationComponent notificationComponent;
    
	@Autowired
	private ProfileComponent profileComponent;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
    	
    	String cookieValue=null;
    	cookieValue =(String) request.getSession().getAttribute("redirecturl");
        String targetUrl = determineTargetUrl(authentication,cookieValue,request);
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
        HttpSession session = request.getSession(false);

        
        if (session != null) {
        	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            LoggedUser user = new LoggedUser(userDetails, activeUserStore);
            session.setAttribute("loggedUser", user);
        	// setting the notfication count
    		List<Profile> profile = profileComponent.getProfile(userDetails.getUserId());

    		if (!profile.isEmpty()) {
    			request.getSession().setAttribute("notification",
    					notificationComponent.getNotificationCount(profile.get(0).getId()));
    		}
        }
        
        
        

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /*
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     */
    protected String determineTargetUrl(Authentication authentication,String cookieValue,HttpServletRequest request) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
            
        }

        if (isAdmin(roles) || isUser(roles)) {
            url = cookieValue !=null ? cookieValue:"/" ;
            request.getSession().setAttribute("authenticated",true);
            request.getSession().setAttribute("authenticationuser",authentication);
        } else {
            url = "/accessDenied";
        }

        return url;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}