package com.spring.security.social.login.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.security.social.login.example.database.model.Coordinator;
import com.spring.security.social.login.example.database.model.RegisterForASymposium;
import com.spring.security.social.login.example.database.model.Subscribe;
import com.spring.security.social.login.example.database.model.Symposium;
import com.spring.security.social.login.example.dto.SocialUser;
import com.spring.security.social.login.example.email.EmailQueue;
import com.spring.security.social.login.example.service.SymposiumServiceInterface;
import com.spring.security.social.login.example.util.FileUtils;
/**
 * 
 * @author manoj
 *
 *This controller is used to save the symposium events.
 *
 *
 */
@Controller
public class HomeController {

	  @Autowired
	  private SymposiumServiceInterface  sympService;
	  @Value("${imagepath}")
	  private String imagePath;
	  
	  @Autowired
	  private EmailQueue queue;
	  
	 private static final Log logger = LogFactory.getLog(HomeController.class);

	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
	     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	     sdf.setLenient(true);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	 }
	  
    @RequestMapping(value = {"/symposiumstep1", "/symposium"}, method = RequestMethod.GET)
    public ModelAndView postSymposium(HttpServletRequest request,Principal pricipal) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        model.addObject("symposium", new Symposium());
        model.setViewName("symppostpage");
        model.addObject("user", getUser(request));
        String userid=(String) request.getSession().getAttribute("userId");
        model.addObject("step1", true);
        System.out.println(pricipal !=null ? pricipal.getName():null);
        Boolean authenticated=(Boolean)request.getSession().getAttribute("authenticated");
        
        if(authenticated!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }
    @RequestMapping(value = "/symposiumstep2", method = RequestMethod.POST)
    public ModelAndView symposiumstep1(@ModelAttribute Symposium symposium,Principal pricipal,HttpServletRequest request) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("symposium",symposium);
        model.setViewName("symppostpage");
        System.out.println(pricipal !=null ? pricipal.getName():null);
        Boolean authenticated=(Boolean)request.getSession().getAttribute("authenticated");
        model.addObject("step2", true);
        Symposium saveNewSymposium = sympService.SaveNewSymposium(symposium); 
        request.getSession().setAttribute("symposium", saveNewSymposium);
        for (Coordinator cor : symposium.getCoordinatorsAsList()) {
        	if(cor.isValid()){
        	cor.setSymposium(saveNewSymposium);
        	sympService.saveCordinator(cor); 
        	}
		}
        
        if(authenticated!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }
    @RequestMapping(value =  "/symposiumstep3", method = RequestMethod.POST)
    public ModelAndView symposiumstep2(Symposium symposium,Principal pricipal,HttpServletRequest request) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("symposium",symposium);
        model.setViewName("symppostpage");
        System.out.println(pricipal !=null ? pricipal.getName():null);
        Boolean authenticated=(Boolean)request.getSession().getAttribute("authenticated");
        Symposium symposiumSessionObj =(Symposium) request.getSession().getAttribute("symposium");
        if(symposium.getFields()!=null){
        	symposiumSessionObj.setFields(symposium.getFields());
            symposium.getFields().setSymposium(symposiumSessionObj);
            sympService.updateSymposium(symposiumSessionObj);
            sympService.saveREgisterFields(symposium.getFields());
        
        }
        
        request.getSession().setAttribute("symposium", symposiumSessionObj);
        model.addObject("step3", true);
        if(authenticated!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }
    
    @RequestMapping(value = "/symposium", method = RequestMethod.POST)
    public ModelAndView symposiumstep3(Symposium symposium,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String userid=(String) request.getSession().getAttribute("userId");
        symposium.setUserId(userid);
        Symposium symposiumSessionObj =(Symposium) request.getSession().getAttribute("symposium");
        symposiumSessionObj.setPaymentType(symposium.getPaymentType());
        symposiumSessionObj.setPaymentDetail(symposium.getPaymentDetail());
        symposiumSessionObj.setUserId(userid);
        Symposium saveNewSymposium = sympService.updateSymposium(symposiumSessionObj);
        ModelAndView model = new ModelAndView("redirect:/symposiumview/"+saveNewSymposium.getSymposiumid()+"/"+saveNewSymposium.getName()+"");
        model.addObject("title", "Login Page");
        model.addObject("symposiumview",saveNewSymposium);
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        model.addObject("user", getUser(request));
        String userid=(String) request.getSession().getAttribute("userId");
        model.addObject("symposium", sympService.getSymposiumByUserId(userid));
        model.addObject("symposiumname", sympService.getSymposiumName(userid));
        model.setViewName("dashboard");	
        Boolean authenticated=(Boolean)request.getSession().getAttribute("authenticated");
        if(authenticated!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }
    
    @RequestMapping(value = "/dashboard/{dashboard}", method = RequestMethod.GET)
    public ModelAndView dashboardview(@PathVariable String dashboard,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        String userid=(String) request.getSession().getAttribute("userId");
        model.addObject("symposiumname", sympService.getSymposiumName(userid));
        model.addObject("symposium", sympService.getSymposiumsBySymposiumId(userid, dashboard));
        model.setViewName("dashboard");	
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }
    
    @RequestMapping(value = "/symposiumview/{symposiumId}/{symposiumname}", method = RequestMethod.GET)
    public ModelAndView viewSymposium(@PathVariable String symposiumId,@PathVariable String symposiumname, HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        model.addObject("symposiumview", sympService.getSymposium(symposiumId));
        model.setViewName("symposiumview");
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        	model.addObject("personid", socialUser.getUserId());
        }
        return model;
    }
    
    @RequestMapping(value = "/registerforsymposium", method = RequestMethod.POST)
    public ModelAndView registerForSymposium(RegisterForASymposium  regsymposium, HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        Symposium symposium = sympService.getSymposium(regsymposium.getSymposiumIddoto());
        regsymposium.setSymposium(symposium); 
        regsymposium.setIsMailSent(Boolean.FALSE);
        sympService.regsiterForSymposium(regsymposium);
        model.addObject("symposiumview",symposium );
        model.setViewName("redirect:/symposiumview/"+regsymposium.getSymposiumIddoto()+"/"+symposium.getName());
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        List<RegisterForASymposium> list=sympService.getSymposiumRegistrations(symposium.getUserId());
        
        
        
       /* queue.sendSymposiumRegistrationEmail(symposium.getRegEmail(), symposium.getName(), "3");*/
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }
    
    private String getUser(HttpServletRequest request) {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
            SocialUser socialUser=(SocialUser)principal;
            System.out.println(socialUser.getUserId());
            if(socialUser !=null && socialUser.getUserId()!=null){
            request.getSession().setAttribute("userId",socialUser.getUserId());
            request.getSession().setAttribute("user",socialUser);
            }
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
    @RequestMapping(value = "/addcokieforredirection", method = RequestMethod.GET)
    public void addcokkie(@RequestParam("redirecturl") String redirecturl,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	request.getSession().setAttribute("redirecturl",redirecturl);
    	
    }
    
    @RequestMapping(value="/upload1", method=RequestMethod.POST)
    public @ResponseBody String  imageUpload(@RequestParam("file")  MultipartFile file,@RequestParam("symposiumId")  String symposiumId,Model model,HttpServletRequest request) throws IOException {
		
			String filename = file.getOriginalFilename();
			String id=UUID.randomUUID().toString();
			String Imagedirectorypath=imagePath;
			File Imagedirectory = new File(imagePath);
			if (!Imagedirectory.exists()) {
				if (Imagedirectory.mkdir()) {
					logger.debug("Directory created successfully");
				} else {
					logger.debug("Failed to create directory!");
				}
			}
			FileUtils.MakeDirectory(Imagedirectorypath);
			//count number of files
			 
			 int filesCount = FileUtils.getCount(Imagedirectorypath,null);
			 int thumbCount=FileUtils.getCount(Imagedirectorypath,FileUtils.THUMB);
			 
			 //FileNames
			 String name=FileUtils.getFilePath(filename, filesCount,null);
			 String thumbName=FileUtils.getFilePath(filename,thumbCount,"_thumb");
			 
			 //File path to save
			 
			 String finalpath=(Imagedirectorypath + "/"+ name);
			 String thumbpath=FileUtils.getFullPath(Imagedirectorypath,thumbName);
			 
			 //link path to store in db
			 String Linkpath="/"+"Images"+"/"+name;
			 String thumbLinkPath="/"+"Images"+"/"+thumbName;

			 Symposium symposium = sympService.getSymposium(symposiumId);
			 //set the image url in sympsoium
			 symposium.setImageUrl(Linkpath);
			 symposium.setCompressedPath(thumbLinkPath);
	     if (!file.isEmpty()) {
	         try {
	             byte[] bytes = file.getBytes();
	             File finalImagepath = new File(finalpath);
	             BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(finalImagepath));
	             stream.write(bytes);
	             stream.close();
	             boolean isCompressed=FileUtils.CompressImageFile(file, thumbpath);
                 if(!isCompressed){
                	 symposium.setCompressedPath(Linkpath);
                	 
                 }
	            
	         } catch (Exception e) {
	        	 System.out.println(e.getMessage());  
	         }
	     } 
	     sympService.updateSymposium(symposium);
	        return Linkpath;
    }
	
    @RequestMapping(value = "/subscribeemail", method = RequestMethod.POST)
    @ResponseBody
    public String subscribeemail( Subscribe subscribe,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	
    	sympService.saveSubcribtion(subscribe); 
    	return "success";
    }
    
    
	//code to edit symposiums 
    @RequestMapping(value = "/editsymposium/{symposiumId}", method = RequestMethod.GET)
    public ModelAndView editSymposium(@PathVariable String symposiumId,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	 ModelAndView model = new ModelAndView();
         model.addObject("title", "Login Page");
         model.addObject("symposiumview", sympService.getSymposium(symposiumId));
         model.setViewName("editsymposium");
         return model;
    }
    
    @RequestMapping(value = "/editsymposium", method = RequestMethod.POST)
    public ModelAndView editSymposium(Symposium symposium,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String userid=(String) request.getSession().getAttribute("userId");
        symposium.setUserId(userid);
        Symposium saveNewSymposium = sympService.updateSymposium(symposium);
        if(symposium.getFields()!=null){
            symposium.getFields().setSymposium(saveNewSymposium);
            sympService.saveREgisterFields(symposium.getFields());
        }
        for (Coordinator cor : symposium.getCoordinators()) {
        	if(cor.isValid()){
        	cor.setSymposium(saveNewSymposium);
        	sympService.saveCordinator(cor); 
        	}
		}
        
        ModelAndView model = new ModelAndView("redirect:/symposiumview/"+saveNewSymposium.getSymposiumid()+"/"+saveNewSymposium.getName()+"");
        model.addObject("title", "Login Page");
        model.addObject("symposiumview",saveNewSymposium);
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
    }

    
 
	
}
