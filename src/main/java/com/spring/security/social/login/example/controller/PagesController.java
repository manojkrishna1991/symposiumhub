package com.spring.security.social.login.example.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.security.social.login.example.database.model.Event;
import com.spring.security.social.login.example.database.model.Greeting;
import com.spring.security.social.login.example.database.model.HelloMessage;
import com.spring.security.social.login.example.database.model.Profile;
import com.spring.security.social.login.example.database.model.User;
import com.spring.security.social.login.example.datasource.ProfileComponent;
import com.spring.security.social.login.example.dto.LocalUser;
import com.spring.security.social.login.example.dto.SocialUser;
import com.spring.security.social.login.example.service.ActiveUserStore;
import com.spring.security.social.login.example.service.LoggedUser;
import com.spring.security.social.login.example.service.SymposiumServiceInterface;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 22/3/16
 */
@RestController
public class PagesController {

	@Autowired
	private SymposiumServiceInterface sympService;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Resource
	private SessionRegistry sessionRegistry;

	@Value("${imagepath}")
	private String imagePath;
	@Resource
	private GreetingController greeting;
	@Resource
	private ActiveUserStore activeUserStore;
	@Autowired
	private ProfileComponent profile;

	private static final Log logger = LogFactory.getLog(PagesController.class);

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Login Page");
		/**
		 * model.addObject("symposiumname",
		 * sympService.getSymposiumName(userid)); is used to get all the
		 * symposium
		 */

		model.addObject("symposium", sympService.getSymposium());
		model.setViewName("home");
		Boolean socialUser = (Boolean) request.getSession().getAttribute("authenticated");
		Authentication auth = (Authentication) request.getSession().getAttribute("authenticationuser");

		if (socialUser != null) {
			model.addObject("authenticated", Boolean.TRUE);
			model.addObject("auth", auth);
		}

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Login Page");
		model.setViewName("login");
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
		// new symposiums from mongo

		/*
		 * Query query = new Query().with(new Sort(Sort.Direction.DESC,
		 * "dateOfEvent"));
		 * query.addCriteria(Criteria.where("type").is("symposium"));
		 * model.addObject("newsymposium", mongoTemplate.find(query,
		 * Event.class));
		 */
		if (socialUser != null) {
			model.addObject("authenticated", Boolean.TRUE);
			model.addObject("auth", auth);
		}

		return model;
	}

	@RequestMapping(value = { "/userpage" }, method = RequestMethod.GET)
	public ModelAndView userPage(HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring security social login Hello World");
		model.addObject("user", getUser(request));
		model.setViewName("user");
		LocalUser localUser = (LocalUser) request.getSession().getAttribute("user");
		if (localUser != null) {
			model.addObject("authenticated", Boolean.TRUE);
		}
		return model;
	}

	@RequestMapping(value = { "/accessdenied" }, method = RequestMethod.GET)
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
			LocalUser socialUser = (LocalUser) principal;
			System.out.println(socialUser.getUserId());
			request.getSession().setAttribute("userId", socialUser.getUserId());
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@RequestMapping(value = "/message/{roomName}", method = RequestMethod.GET)
	public ModelAndView Message(@PathVariable String roomName, Model model, HttpServletRequest request) {

		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = user.getUsername();

		logger.info(username);

		Query query = new Query();

		query.limit(10);

		query.with(new Sort(Sort.Direction.DESC, "createdDate"));

		query.addCriteria(Criteria.where("roomName").is(roomName));

		List<HelloMessage> find = mongoTemplate.find(query, HelloMessage.class);

		List<UserDetails> allPrincipals = (List<UserDetails>) (List<?>) sessionRegistry.getAllPrincipals();

		boolean empty = sessionRegistry.getAllPrincipals().isEmpty();

		LoggedUser users = (LoggedUser) request.getSession().getAttribute("loggedUser");

		for (UserDetails userDetails : users.getActiveUserStore().users) {
			System.out.println(userDetails.getUsername());
		}

		logger.info(empty);
		model.addAttribute("chatHistory", find);
		model.addAttribute("userName", user.getUsername());
		model.addAttribute("roomName", roomName);
		model.addAttribute("users", users.getActiveUserStore().users);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@MessageMapping("/hello")
	public void greeting(HelloMessage message) throws Exception {
		mongoTemplate.save(message);
		greeting.afterTradeExecuted(message);
	}

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView chatPage(Model model, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("chat");
		return modelAndView;

	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView feed(Model model, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "dateOfEvent"));
		
		query.limit(5);

		modelAndView.addObject("conference", mongoTemplate.find(query, Event.class));
		
		modelAndView.addObject("symposium", sympService.getSymposiumByLimit());
		
		UserDetails user =null;
		
		try{
		
		user= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		if(user!=null){
			List<Profile> currentProfile = profile.getProfile(user.getUserId());
			
			if(!currentProfile.isEmpty())
			{
			modelAndView.addObject("activity",profile.getFreindsActivity(user.getUserId(), currentProfile.get(0).getId()));
			}
		}
		modelAndView.setViewName("feed");
		
		return modelAndView;

	}

}
