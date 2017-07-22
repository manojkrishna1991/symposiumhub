package com.symposiumhub.controller;

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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.Enum.FormType;
import com.symposiumhub.Enum.Sympoconstants;
import com.symposiumhub.dto.SocialUser;
import com.symposiumhub.model.Event;
import com.symposiumhub.model.SymposiumRegistrationFieldsMongo;
import com.symposiumhub.util.FileUtils;

/**
 * 
 * @author manojramana
 *commenting event controller migration from mongodb
 */
@Controller
public class EventController {
/*
	@Autowired
	private MongoTemplate mongoTemplate;
	@Value("${imagepath}")
	private String imagePath;

	private static final Log logger = LogFactory.getLog(EventController.class);

	@RequestMapping(value = { "/post-a-conference"}, method = RequestMethod.GET)
	public ModelAndView postAConference(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("post-conference");
		Event event=new Event();
		String Url=request.getRequestURI();
		if(!StringUtils.isEmpty(Url) && Url.equalsIgnoreCase("/post-a-conference")){
			model.addObject("type", Sympoconstants.conference.name());
			
		}
		if(!StringUtils.isEmpty(Url) && Url.equalsIgnoreCase("/post-a-symposium")){
			model.addObject("type", Sympoconstants.symposium.name());
		}
		model.addObject("step1",true);
		model.addObject("event",event);
		 
		return model;
	}
    
	@RequestMapping(value = { "/post-a-conference" ,"/conferenceStep2"}, method = RequestMethod.POST)
	public ModelAndView saveAConference(Event event,HttpServletRequest request) {
		
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		

		ModelAndView model = new ModelAndView();
		
		String id= event.getId()  ==null ? UUID.randomUUID().toString():event.getId();
		
		event.setId(id);
		
		List<String> compressedPath=new ArrayList<>();
		compressedPath.add("/resources/assets/images/wireframe/image.png");
		
		event.setCompressedPath(compressedPath);
		String Url=request.getRequestURI();
		
	
		event.setUserId(user.getUserId());
		
		event.setFormType(FormType.defaultform.name());
	
		mongoTemplate.save(event);
		model.addObject("step2",true);
		model.setViewName("redirect:/viewconference/" + id);
		return model;
	}
	
	@RequestMapping(value = { "/edit-a-conference/{eventId}" }, method = RequestMethod.GET)
	public ModelAndView editAConference(@PathVariable String eventId,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		Event findById = mongoTemplate.findById(eventId, Event.class);
		
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user!=null && findById!=null){
		String eventUserId=findById.getUserId();
	    String socialUserId=user.getUserId();
        if((!StringUtils.isEmpty(eventUserId)) && (!StringUtils.isEmpty(socialUserId)))
        {
        	if(eventUserId.equalsIgnoreCase(socialUserId)){
        		model.setViewName("edit-conference");
        	}
        	else{
        		model.setViewName("home");
        	}
        }
		}
		else{
			model.setViewName("home");
		}
		model.addObject("eventDate",DateFormatUtils.format(findById.getDateOfEvent(), "dd/MM/yyyy") );
		
		model.addObject("event", findById);
		 
		return model;
	
	}
	
	

	@RequestMapping(value = { "/viewconference/{conferenceId}" }, method = RequestMethod.GET)
	public ModelAndView viewAConference(@PathVariable String conferenceId,HttpServletRequest request ) {
		ModelAndView model = new ModelAndView();
		model.setViewName("view-conference");
		Event findById = mongoTemplate.findById(conferenceId, Event.class);
		model.addObject("conference", findById);
		String requestUrl=request.getRequestURI();
		if(!StringUtils.isEmpty(request.getQueryString())){
			requestUrl=requestUrl+request.getQueryString();
		}
		
		model.addObject("pageurl", requestUrl);
		SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
		if(socialUser!=null && findById!=null){
		String eventUserId=findById.getUserId();
	    String socialUserId=socialUser.getUserId();
        if((!StringUtils.isEmpty(eventUserId)) && (!StringUtils.isEmpty(socialUserId)))
        {
        	if(eventUserId.equalsIgnoreCase(socialUserId)){
        	model.addObject("authenticated", Boolean.TRUE);
        	}
        	else{
        		model.addObject("authenticated", Boolean.FALSE);
        	}
        }
		}
		return model;
	}

	@RequestMapping(value = { "/viewconference/{conferenceId}/{name}" }, method = RequestMethod.GET)
	public ModelAndView viewAConferencewithName(@PathVariable String conferenceId,HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("view-conference");
		Event findById = mongoTemplate.findById(conferenceId, Event.class);
		model.addObject("conference", findById);
		String requestUrl=request.getRequestURI();
		if(!StringUtils.isEmpty(request.getQueryString())){
			requestUrl=requestUrl+request.getQueryString();
		}
		
		model.addObject("pageurl", requestUrl);
		SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
		if(socialUser!=null && findById!=null){
		String eventUserId=findById.getUserId();
	    String socialUserId=socialUser.getUserId();
        if((!StringUtils.isEmpty(eventUserId)) && (!StringUtils.isEmpty(socialUserId)))
        {
        	if(eventUserId.equalsIgnoreCase(socialUserId)){
        	model.addObject("authenticated", Boolean.TRUE);
        	}
        	else{
        		model.addObject("authenticated", Boolean.FALSE);
        	}
        }
		}
		return model;
	}

	@RequestMapping(value = { "/uploadphotos/{conferenceId}" }, method = RequestMethod.GET)
	public ModelAndView uploadphotos(@PathVariable String conferenceId) {
		ModelAndView model = new ModelAndView();
		model.setViewName("uploadphotos");
		Event findById = mongoTemplate.findById(conferenceId, Event.class);
		model.addObject("conference", findById);
		return model;
	}

	@RequestMapping(value = "/upload-photos", method = RequestMethod.POST)
	public ModelAndView imageUpload(@RequestParam("file") List<MultipartFile> files,
			@RequestParam("conferenceId") String conferenceId, Model model, HttpServletRequest request)
			throws IOException {

		Event conference = mongoTemplate.findById(conferenceId, Event.class);
		List<String> imageList = new ArrayList<>();
		List<String> imageListCompressedPath = new ArrayList<>();

		for (MultipartFile file : files) {

			String filename = file.getOriginalFilename();
			if(StringUtils.isEmpty(filename)){
				continue;
			}
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

			// set the image url in sympsoium
			imageList.add(Linkpath);
			imageListCompressedPath.add(thumbLinkPath);

			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					File finalImagepath = new File(finalpath);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(finalImagepath));
					stream.write(bytes);
					stream.close();
					boolean isCompressed = FileUtils.CompressImageFile(file, thumbpath);
					if (!isCompressed) {
						imageListCompressedPath.add(thumbLinkPath);

					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}
		if (conference.getImageUrl() == null || conference.getImageUrl().isEmpty()) {
			conference.setImageUrl(imageList);
			conference.setCompressedPath(imageListCompressedPath);
		} else {
			conference.getImageUrl().addAll(imageList);
			conference.getCompressedPath().addAll(imageListCompressedPath);
		}
		mongoTemplate.save(conference);
		ModelAndView models = new ModelAndView("redirect:/viewconference/" + conferenceId);
		return models;
	}

	@RequestMapping(value = "/viewconference", method = RequestMethod.GET)
	public ModelAndView viewconference(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Login Page");
		*//**
		 * model.addObject("symposiumname",
		 * sympService.getSymposiumName(userid)); is used to get all the
		 * symposium
		 *//*
		Query query = new Query().with(new Sort(Sort.Direction.DESC, "dateOfEvent"));

		model.addObject("conference", mongoTemplate.find(query, Event.class));
		model.setViewName("view-all-conference");

		return model;
	}
	
	  
    @RequestMapping(value = "/eventregistration/{eventId}", method = RequestMethod.GET)
    public ModelAndView multiLogin(@PathVariable String eventId,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        *//**
         *   model.addObject("symposiumname", sympService.getSymposiumName(userid)); is used to get all the symposium
         *//*

        model.addObject("eventregistration", new SymposiumRegistrationFieldsMongo() );
        Event findById = mongoTemplate.findById(eventId, Event.class);
        model.addObject("event", findById);
        model.setViewName("multilogin");
       return model;
    }
   
	  
    @RequestMapping(value = "/eventregistration", method = RequestMethod.POST)
    public ModelAndView eventRegistrationField(SymposiumRegistrationFieldsMongo eventregistration,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        *//**
         *   model.addObject("symposiumname", sympService.getSymposiumName(userid)); is used to get all the symposium
         *//*

        mongoTemplate.save(eventregistration);
        
        Event findById = mongoTemplate.findById(eventregistration.getEventId(), Event.class);
        
        model.addObject("event", findById);
        model.setViewName("multilogin");
        model.addObject("isRegistered", Boolean.TRUE);
       return model;
    }
    
*/
}
