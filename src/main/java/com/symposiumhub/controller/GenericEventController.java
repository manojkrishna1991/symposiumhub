package com.symposiumhub.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.Enum.EventTypes;
import com.symposiumhub.Mongo.DynamicFormDataDroplet;
import com.symposiumhub.Mongo.SymposiumDynamicFormHandler;
import com.symposiumhub.Mongo.Values;
import com.symposiumhub.datasource.EventRepositoryComponent;
import com.symposiumhub.datasource.RegisterForEventComponent;
import com.symposiumhub.model.GenericEvent;
import com.symposiumhub.model.GenericEventRegistrationFields;
import com.symposiumhub.service.RegistrationScheduler;
import com.symposiumhub.service.UserService;
import com.symposiumhub.util.FileUtils;

/**
 * 
 * @author manoj
 *
 *         This controller is used for workshops
 *
 *
 */
@Controller
public class GenericEventController extends BaseController {

	private static final Log logger = LogFactory.getLog(CSVFileDownloadController.class);

	@Autowired
	private EventRepositoryComponent eventRepository;
	@Autowired
	private RegisterForEventComponent registerForEventComponet;
	@Autowired
	private RegistrationScheduler registrationScheduler;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private UserService userService;

	@Value("${imagepath}")
	private String imagePath;

	@RequestMapping(value = "/post-event/{eventType}", method = RequestMethod.GET)
	public ModelAndView postWorkShop(@PathVariable String eventType, HttpServletRequest request, Principal pricipal)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Event");
		model.addObject("event", new GenericEvent());
		model.addObject("eventType", eventResolver(eventType));
		model.setViewName("/post/event");
		model.addObject("eventType", eventType);
		model.addObject("step1", true);
		return model;
	}

	@RequestMapping(value = { "/post-event", "/step2" }, method = RequestMethod.POST)
	public ModelAndView step2(GenericEvent event, HttpServletRequest request, HttpServletResponse response,
			Principal pricipal) throws ServletException, IOException {

		ModelAndView model = new ModelAndView();
		model.addObject("title", event.getName());
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		event.setUserId(user.getUserId());
		eventRepository.add(event);
		request.getSession().setAttribute(EVENT_ID, event.getEventid());
		model.addObject("step2", true);
		model.addObject("eventName", event.getName());
		// String eventUrl = "/event/" + event.getId() + "/" + event.getName();
		model.setViewName("redirect:/registrationfields/"+event.getEventid());
		return model;

	}

	@RequestMapping(value = "/step3/{eventId}", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView step3(@PathVariable Integer eventId, HttpServletRequest request, HttpServletResponse response,
			Principal pricipal) throws ServletException, IOException {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		GenericEvent event = eventRepository.findEventByUserId(eventId, user.getUserId());
		ModelAndView model = new ModelAndView();
		model.addObject("title", event.getName());
		event.setEventid(String.valueOf(eventId));
		model.addObject("step3", true);
		model.addObject("eventName", event.getName());
		request.getSession().setAttribute(EVENT_ID, String.valueOf(eventId));
		model.setViewName("/post/event");
		return model;

	}

	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public ModelAndView stepSuccess(GenericEvent event, HttpServletRequest request, HttpServletResponse response,
			Principal pricipal) throws ServletException, IOException {

		ModelAndView model = new ModelAndView();
		String eventIdString = (String) request.getSession().getAttribute(EVENT_ID);
		GenericEvent eventFromDb = eventRepository.findEventById(Integer.valueOf(eventIdString));
		event.setEventid((String) request.getSession().getAttribute(EVENT_ID));
		eventFromDb.setPaymentDetail(event.getPaymentDetail());
		event = setEventPhoto(event);
		eventFromDb.setImageUrl(event.getImageUrl());
		eventRepository.updateEvent(eventFromDb);
		model.setViewName("redirect:/event/" + eventIdString + "/" + eventFromDb.getName());
		return model;

	}

	@RequestMapping(value = "/event/{eventId}/{eventName}", method = RequestMethod.GET)
	public ModelAndView viewSymposium(@PathVariable String eventId, @PathVariable String eventName,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Event");
		GenericEvent event = null;
		if (!StringUtils.isEmpty(eventId)) {
			event = eventRepository.findEventById(Integer.parseInt(eventId));
		}
		if (event == null) {
			event = new GenericEvent();
		}
		if (event.getEventid() != null) {
			event.setCoordinatorsAsList(eventRepository.findCoordinatorById(Integer.parseInt(event.getEventid())));
		}
		model.addObject("event", event);
		try{
			model.addObject("user", userService.getUserById(event.getUserId()));

		}catch(Exception e){
			logger.warn(e.toString());
		}
		model.setViewName("/post/view-event");

		return model;
	}

	@RequestMapping(value = "/edit-event/{eventId}", method = RequestMethod.GET)
	public ModelAndView editWorkShop(GenericEvent event, @PathVariable String eventId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();

		if (!StringUtils.isEmpty(eventId)) {
			event = eventRepository.findEventById(Integer.valueOf(eventId));
		}
		model.addObject("title", event.getEventType());
		if (event == null) {
			event = new GenericEvent();
		}
		if (event.getEventid() != null) {
			event.setCoordinatorsAsList(eventRepository.findCoordinatorById(Integer.parseInt(event.getEventid())));
			GenericEventRegistrationFields findRegistrationFieldsById = registerForEventComponet
					.findRegistrationFieldsById(Integer.valueOf(event.getEventid()));
			event.setFields(findRegistrationFieldsById);
		}

		model.addObject("eventDate", DateFormatUtils.format(event.getDateOfEvent(), "dd/MM/yyyy"));
		model.addObject("event", event);
		model.setViewName("/post/edit-event");

		return model;
	}

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public ModelAndView saveWorkShop(String id, GenericEvent event, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Event");

		if (!StringUtils.isEmpty(event.getEventid())) {
			event = setEventPhoto(event);
			eventRepository.updateEvent(event);
			eventRepository.updateCoordinatorEvent(event.getCoordinatorsAsList(), event);
			model.addObject("message", "success");
		}
		model.setViewName("redirect:/edit-event/" + event.getEventid());
		return model;
	}

	@RequestMapping(value = "/registerForEvent/{eventId}", method = RequestMethod.GET)
	public ModelAndView registerForEvent(@PathVariable String eventId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.setViewName("registerForEvent");
		model.addObject("eventId", eventId);
		model.addObject("preview",request.getParameter("preview"));
		model.addObject("event", eventRepository.findEventById(Integer.valueOf(eventId)));
		Query searchUserQuery = new Query(Criteria.where("symposimuId").is(eventId));
		List<SymposiumDynamicFormHandler> find = mongoTemplate.find(
				searchUserQuery, SymposiumDynamicFormHandler.class);
		model.addObject("symposium", find);
		model.addObject("formexist", find.isEmpty());
		return model;

	}
	
	
	@RequestMapping(value = "/eventregistration", method = RequestMethod.POST)
	public ModelAndView SaveRegistrationData(
			Map<String, String> allRequestParams,String eventId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		StringBuilder sb=new StringBuilder();
		sb.append("redirect:/registerForEvent/");
		sb.append(eventId);
		sb.append("?message=success&messagetext=");
		sb.append("You have successfully registered for this event");
		model.setViewName(sb.toString());
		
		DynamicFormDataDroplet dynamicFormDataDroplet = new DynamicFormDataDroplet();
		dynamicFormDataDroplet.setSymposiumID(eventId);
		List<Values> values = new ArrayList<>();
		Map<String, String[]> params = request.getParameterMap();
		
		for (Entry<String, String[]> entry : params.entrySet())
		{
			String key = entry.getKey() ;
			String value = StringUtils.join(entry.getValue(), ',');
			Values valueObject = new Values();
			valueObject.setId(UUID.randomUUID().toString());
			valueObject.setName(key);
			valueObject.setValue(value);
			values.add(valueObject);
		}
	

		dynamicFormDataDroplet.setValues(values);
		mongoTemplate.save(dynamicFormDataDroplet);
		
		return model;
		
	}
	
	
	@RequestMapping(value = "/registrations/{eventId}", method = RequestMethod.GET)
	public ModelAndView eventRegistrations(@PathVariable Integer eventId,HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model = new ModelAndView();
		model.setViewName("dynamicRegistrationDroplet");
		Query searchUserQuery = new Query(Criteria.where("symposiumID").is(String.valueOf(eventId)));
		List<DynamicFormDataDroplet> find = mongoTemplate.find(searchUserQuery,
				DynamicFormDataDroplet.class);
		Query searchUserQuery1 = new Query(Criteria.where("symposimuId")
				.is(String.valueOf(eventId)));
		List<SymposiumDynamicFormHandler> find1 = mongoTemplate.find(
				searchUserQuery1, SymposiumDynamicFormHandler.class);
		model.addObject("DynamicFormForm", find);
		model.addObject("symposium", find1);
		return model;

	}
	
//	@RequestMapping(value = "/registrations/{eventId}", method = RequestMethod.GET)
//	public ModelAndView eventRegistrations(@PathVariable Integer eventId,HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		ModelAndView model = new ModelAndView();
//		model.setViewName("registrations");
//		if(StringUtils.isEmpty(eventId) && StringUtils.isEmpty(user.getUserId())){
//          model.setViewName("/");
//		}
//		else{
//			GenericEvent event=eventRepository.findEventByUserId(eventId, user.getUserId());
//			if(event!=null){
//				List<Registration> registrations = registerForEventComponet.getRegistrations(eventId);
//				model.addObject("registrations", registrations);
//				
//			}
//		}
//		return model;
//
//	}


//	@RequestMapping(value = "/eventregistration", method = RequestMethod.POST)
//	public ModelAndView eventRegistrationField(Registration registration, HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Event Registration");
//		model.addObject("event", eventRepository.findEventById(registration.getEventid()));
//		model.addObject("registration", registration);
//		registerForEventComponet.add(registration);
//		model.addObject("registraionFields",
//				registerForEventComponet.findRegistrationFieldsById(registration.getEventid()));
//		model.setViewName("registration");
//		model.addObject("isRegistered", Boolean.TRUE);
//		return model;
//	}

	@RequestMapping(value = "/view-event/{eventType}", method = RequestMethod.GET)
	public ModelAndView viewWorkShop(@PathVariable String eventType) {
		ModelAndView model = new ModelAndView();
		model.addObject("ttitle", "Event");
		List<GenericEvent> eventList = eventRepository.getAllEvent(eventType);
		model.addObject("eventType", eventResolver(eventType));
		model.addObject("event", eventList);
		model.setViewName("/post/view-all-event");
		return model;
	}

	@RequestMapping(value = "/demoServiceMethod", method = RequestMethod.GET)
	public ModelAndView demoServiceMethod() {
		ModelAndView model = new ModelAndView();
		registrationScheduler.demoServiceMethod();
		model.addObject("title", "Post Event");
		List<GenericEvent> eventList = eventRepository.getAllEvent("symposium");
		model.addObject("eventType", eventResolver("symposium"));
		model.addObject("event", eventList);
		model.setViewName("/post/view-all-event");
		return model;
	}
	
	@RequestMapping(value = {"/postemail","/selectEvent"}, method = RequestMethod.GET)
	public ModelAndView renderPostForEmail() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Post Event");
		model.setViewName("postemail");
		return model;
		
	}

	private String eventResolver(String event) {

		String eventName = null;

		switch (event) {
		case WORKSHOPURL:
			eventName = EventTypes.workshop.name();
			break;
		case GUESTLECTUREURL:
			eventName = EventTypes.guestlecture.name();
			break;
		case HACKATHON:
			eventName = EventTypes.hackathon.name();
			break;
		case SYMPOSIUM:
			eventName = EventTypes.symposium.name();
			break;
		case CONFERENCE:
			eventName = EventTypes.conference.name();
			break;

		}

		return eventName;

	}

	private GenericEvent setEventPhoto(GenericEvent event) {
		if (event.getFile() != null && !event.getFile().isEmpty()) {
			MultipartFile file = event.getFile();
			String filename = file.getOriginalFilename();
			String id = UUID.randomUUID().toString();
			String Imagedirectorypath = imagePath;
			File Imagedirectory = new File(imagePath);
			if (!Imagedirectory.exists()) {
				if (Imagedirectory.mkdir()) {
					logger.debug("Directory created successfully");
				} else {
					logger.debug("Failed to create directory!");
				}
			}
			FileUtils.MakeDirectory(Imagedirectorypath);
			// count number of files

			int filesCount = FileUtils.getCount(Imagedirectorypath, null);
			int thumbCount = FileUtils.getCount(Imagedirectorypath, FileUtils.THUMB);

			// FileNames
			String name = FileUtils.getFilePath(filename, filesCount, null);
			String thumbName = FileUtils.getFilePath(filename, thumbCount, "_thumb");

			// File path to save

			String finalpath = (Imagedirectorypath + "/" + name);
			String thumbpath = FileUtils.getFullPath(Imagedirectorypath, thumbName);

			// link path to store in db
			String Linkpath = "/" + "Images" + "/" + name;
			String thumbLinkPath = "/" + "Images" + "/" + thumbName;

			event.setImageUrl(Linkpath);

			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					File finalImagepath = new File(finalpath);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalImagepath));
					stream.write(bytes);
					stream.close();
					boolean isCompressed = FileUtils.CompressImageFile(file, thumbpath);
					if (!isCompressed) {
						event.setImageUrl(Linkpath);

					}

				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			}
		}
		return event;
	}
	
	

}
