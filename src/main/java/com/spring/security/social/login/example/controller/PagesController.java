package com.spring.security.social.login.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.security.social.login.example.Enum.Sympoconstants;
import com.spring.security.social.login.example.database.model.Event;
import com.spring.security.social.login.example.dto.SocialUser;
import com.spring.security.social.login.example.service.SymposiumServiceInterface;
import com.spring.security.social.login.example.util.FileUtils;
/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 22/3/16	
 */
@RestController
public class PagesController {

	@Autowired
	 private SymposiumServiceInterface  sympService;
	@Autowired
	private MongoTemplate mongoTemplate;
	  @Value("${imagepath}")
	  private String imagePath;
	  
		 private static final Log logger = LogFactory.getLog(PagesController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        /**
         *   model.addObject("symposiumname", sympService.getSymposiumName(userid)); is used to get all the symposium
         */
        
        model.addObject("symposium", sympService.getSymposium());
        model.setViewName("home");
        Boolean  socialUser=(Boolean)request.getSession().getAttribute("authenticated");
        Authentication auth=(Authentication)request.getSession().getAttribute("authenticationuser");
        
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        	model.addObject("auth", auth);
        }
      
        return model;
    }

	@RequestMapping(value = "/viewsymposium", method = RequestMethod.GET)
	public ModelAndView viewsymposium(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Login Page");
		/**
		 * model.addObject("symposiumname",
		 * sympService.getSymposiumName(userid)); is used to get all the
		 * symposium
		 */

		model.addObject("symposium", sympService.getSymposium());
		model.setViewName("symhomepage");
		Boolean socialUser = (Boolean) request.getSession().getAttribute("authenticated");
		Authentication auth = (Authentication) request.getSession().getAttribute("authenticationuser");
//new symposiums from  mongo
		
		/*Query query = new Query().with(new Sort(Sort.Direction.DESC, "dateOfEvent"));
        query.addCriteria(Criteria.where("type").is("symposium"));
		model.addObject("newsymposium", mongoTemplate.find(query, Event.class));*/
		if (socialUser != null) {
			model.addObject("authenticated", Boolean.TRUE);
			model.addObject("auth", auth);
		}

		return model;
	}
    
  
    @RequestMapping(value = {"/userpage"}, method = RequestMethod.GET)
    public ModelAndView userPage(HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring security social login Hello World");
        model.addObject("user", getUser(request));
        model.setViewName("user");
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }

    @RequestMapping(value = {"/accessdenied"}, method = RequestMethod.GET)
    public ModelAndView accessDeniedPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Either username or password is incorrect.");
        model.setViewName("accessdenied");
        return model;
    }

    private String getUser(HttpServletRequest request) {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
            SocialUser socialUser=(SocialUser)principal;
            System.out.println(socialUser.getUserId());
            request.getSession().setAttribute("userId",socialUser.getUserId());
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    
   
    
    
    
}
