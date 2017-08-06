
package com.symposiumhub.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.Enum.EventTypes;
import com.symposiumhub.datasource.EventRepositoryComponent;
import com.symposiumhub.datasource.ProfileComponent;
import com.symposiumhub.dto.LocalUser;
import com.symposiumhub.email.EmailQueue;
import com.symposiumhub.model.Event;
import com.symposiumhub.model.GenericEvent;
import com.symposiumhub.model.HelloMessage;
import com.symposiumhub.model.Profile;
import com.symposiumhub.model.User;
import com.symposiumhub.service.ActiveUserStore;
import com.symposiumhub.service.LoggedUser;
import com.symposiumhub.service.SymposiumServiceInterface;
import com.symposiumhub.service.UserService;

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

	@Autowired
	private EventRepositoryComponent eventRepository;

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
	@Autowired
	private UserService userService;
	@Autowired
	@Qualifier(value = "localUserDetailService")
	private UserDetailsService userDetailService;

	@Autowired
	private EmailQueue queue;

	private static final Log logger = LogFactory.getLog(PagesController.class);

	private String forgetPasswordLink = "http://symposiumhub.com/resetpassword?activationKey=****&username=####";

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

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public ModelAndView forgotpassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Forgot Password");
		model.setViewName("forgotpassword");
		return model;
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ModelAndView sendForgotPassword(String userid, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Forgot Password");
		model.setViewName("forgotpassword");
		User userById = userService.getUserById(userid);

		if (userById == null) {
			model.addObject("error1", true);
			return model;
		}

		userById.setActivationKey(System.currentTimeMillis());

		String userEmailId = userById.getEmailId();
		String userName = userById.getUserId();
		forgetPasswordLink = forgetPasswordLink.replace("****", String.valueOf(userById.getActivationKey()));
		forgetPasswordLink = forgetPasswordLink.replace("####", userName);
		queue.sendResetPassword(userEmailId, userName, forgetPasswordLink);

		userService.SaveorUpdateUser(userById);

		model.addObject("password", true);

		return model;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ModelAndView resetpassword(String activationKey, String username, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Reset Password");

		if (activationKey == null || username == null) {
			model.setViewName("redirect:/");
			return model;
		}

		model.setViewName("resetpassword");

		User userById = userService.getUserById(username);
		model.addObject("user", userById);
		try {
			Long.parseLong(activationKey);
		} catch (Exception e) {
			logger.warn(e.toString());
			model.addObject("error1", true);
			return model;
		}

		if (userById == null || !userById.getActivationKey().equals(Long.parseLong(activationKey))) {
			model.addObject("error1", true);
			return model;
		} else {
			model.addObject("activation", true);
		}

		return model;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public ModelAndView saveresetpassword(String password, String userId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Reset Password");

		User userById = userService.getUserById(userId);

		userById.setPassword(password);
		userById.setActivationKey(System.currentTimeMillis());
		userService.SaveorUpdateUser(userById);

		UserDetails user = (LocalUser) userDetailService.loadUserByUsername(userById.getUserId());

		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, null));

		model.setViewName("redirect:/");

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
		model.setViewName("login");
		model.addObject("message", true);
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

		for (User sympUser : users.getActiveUserStore().users) {
			System.out.println(sympUser.getName());
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

//		Query query = new Query().with(new Sort(Sort.Direction.DESC, "dateOfEvent"));
//
//		query.limit(5);
//
//		modelAndView.addObject("conference", getEvents(EventTypes.conference.name()));
//
//		modelAndView.addObject("symposium", getEvents(EventTypes.symposium.name()));
//
//		modelAndView.addObject("workshop", getEvents(EventTypes.workshop.name()));
//
//		modelAndView.addObject("guestlecture", getEvents(BaseController.GUESTLECTUREURL));
//		modelAndView.addObject("hackathon", getEvents(EventTypes.hackathon.name()));
//		UserDetails user = null;
//
//		try {
//
//			user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		} catch (Exception e) {
//			logger.info(e.getMessage());
//		}
//
//		if (user != null) {
//			List<Profile> currentProfile = profile.getProfile(user.getUserId());
//
//			if (!currentProfile.isEmpty()) {
//				modelAndView.addObject("activity",
//						profile.getFreindsActivity(user.getUserId(), currentProfile.get(0).getId()));
//			}
//		}
		modelAndView.setViewName("home");

		return modelAndView;

	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public ModelAndView messages(Model model, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		UserDetails user = null;
		try {
			user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		if (user != null) {
			List<Profile> currentProfile = profile.getProfile(user.getUserId());
			if (!currentProfile.isEmpty()) {
				modelAndView.addObject("activity",
						profile.getFreindsActivity(user.getUserId(), currentProfile.get(0).getId()));
			}
		}
		modelAndView.setViewName("messages");
		return modelAndView;
	}

	public List<GenericEvent> getEvents(String eventType) {
		List<GenericEvent> event = eventRepository.getAllEvent(eventType);
		if (event.size() > 5) {
			event = event.subList(0, 4);
		}
		return event;
	}

}
