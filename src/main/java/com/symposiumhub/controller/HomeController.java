package com.symposiumhub.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.dto.CommentsDto;
import com.symposiumhub.dto.LocalUser;
import com.symposiumhub.dto.SocialUser;
import com.symposiumhub.dto.SymposiumCommentDto;
import com.symposiumhub.dto.SymposiumCommentsReplyDto;
import com.symposiumhub.dto.SymposiumDto;
import com.symposiumhub.email.EmailQueue;
import com.symposiumhub.model.Coordinator;
import com.symposiumhub.model.Papers;
import com.symposiumhub.model.RegisterForASymposium;
import com.symposiumhub.model.Subscribe;
import com.symposiumhub.model.Symposium;
import com.symposiumhub.model.SymposiumComment;
import com.symposiumhub.model.SymposiumCommentsReply;
import com.symposiumhub.model.User;
import com.symposiumhub.service.SymposiumServiceInterface;
import com.symposiumhub.service.UserService;
import com.symposiumhub.util.FileUtils;
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
	  
	  @Autowired
	  private UserService registrationUserDetailService;
	  
	  @Value("${imagepath}")
	  private String imagePath;
	  @Value("${filepath}")
	  private String filePath;
	  @Autowired
	  private EmailQueue queue;
	  
	 private static final Log logger = LogFactory.getLog(HomeController.class);

	 @InitBinder
	 public void initBinder(WebDataBinder binder) {
	     SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	     sdf.setLenient(true);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	 }
	  
    @GetMapping(value = {"/symposiumstep1", "/symposium"})
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
    @PostMapping(value = "/symposiumstep2")
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
    @PostMapping(value =  "/symposiumstep3")
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
    
    @PostMapping(value = "/symposium")
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
    
//    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
//    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Login Page");
//        model.addObject("user", getUser(request));
//        String userid=(String) request.getSession().getAttribute("userId");
//        model.addObject("symposium", sympService.getSymposiumByUserId(userid));
//        model.addObject("symposiumname", sympService.getSymposiumName(userid));
//        model.setViewName("dashboard");	
//        Boolean authenticated=(Boolean)request.getSession().getAttribute("authenticated");
//        if(authenticated!=null)
//        {
//        	model.addObject("authenticated", Boolean.TRUE);
//        }
//        return model;
//    }
//    
//    @RequestMapping(value = "/dashboard/{dashboard}", method = RequestMethod.GET)
//    public ModelAndView dashboardview(@PathVariable String dashboard,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Login Page");
//        String userid=(String) request.getSession().getAttribute("userId");
//        model.addObject("symposiumname", sympService.getSymposiumName(userid));
//        model.addObject("symposium", sympService.getSymposiumsBySymposiumId(userid, dashboard));
//        model.setViewName("dashboard");	
//        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
//        if(socialUser!=null)
//        {
//        	model.addObject("authenticated", Boolean.TRUE);
//        }
//        return model;
//    }
//    
    @GetMapping(value = "/symposiumview/{symposiumId}/{symposiumname}")
    public ModelAndView viewSymposium(@PathVariable String symposiumId,@PathVariable String symposiumname, HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        SymposiumDto symposium=sympService.getSymposium(symposiumId);
        model.addObject("symposiumview", symposium);
       
        
        model.addObject("comment", symposium.getSymposiumComment());
        model.setViewName("symposiumview");
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        	model.addObject("personid", socialUser.getUserId());
        }
        return model;
    }
    
    @PostMapping(value = "/registerforsymposium")
    public ModelAndView registerForSymposium(RegisterForASymposium  regsymposium, HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        SymposiumDto symposiumDto = sympService.getSymposium(regsymposium.getSymposiumIddoto());
        
        Symposium symposium = new Symposium(symposiumDto);
        
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
            if(principal instanceof LocalUser){
            	LocalUser user=(LocalUser)principal;
                System.out.println(user.getUserId());
                if(user !=null && user.getUserId()!=null){
                SocialUser socialUser=new SocialUser(user.getUserId(),user.getUsername(),"dummy", user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), user.getAuthorities());
                request.getSession().setAttribute("userId",user.getUserId());
                request.getSession().setAttribute("user",socialUser);
            }
            }
            
                if(principal instanceof SocialUser){
                	SocialUser socialUser=(SocialUser)principal;
                    System.out.println(socialUser.getUserId());
                    if(socialUser !=null && socialUser.getUserId()!=null){
                    request.getSession().setAttribute("userId",socialUser.getUserId());
                    request.getSession().setAttribute("user",socialUser);
                }
                }
            
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
    @GetMapping(value = "/addcokieforredirection")
    public void addcokkie(@RequestParam("redirecturl") String redirecturl,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	request.getSession().setAttribute("redirecturl",redirecturl);
    	
    }
    
    @PostMapping(value="/upload1")
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

			 SymposiumDto symposiumDto = sympService.getSymposium(symposiumId);
			 
		        Symposium symposium = new Symposium(symposiumDto);

			 
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
	
    @PostMapping(value = "/subscribeemail")
    @ResponseBody
    public String subscribeemail( Subscribe subscribe,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	
    	sympService.saveSubcribtion(subscribe); 
    	return "success";
    }
    
    
	//code to edit symposiums 
    @GetMapping(value = "/editsymposium/{symposiumId}")
    public ModelAndView editSymposium(@PathVariable String symposiumId,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	 ModelAndView model = new ModelAndView();
         model.addObject("title", "Login Page");
         model.addObject("symposiumview", sympService.getSymposium(symposiumId));
         model.setViewName("editsymposium");
         return model;
    }
    
    @PostMapping(value = "/editsymposium")
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

    @PostMapping(value = "/uploadpapers")
    public ModelAndView uploadpaper(@RequestParam("file")  MultipartFile file,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	String symposiumId=request.getParameter("paperid"); 
    	String userName=request.getParameter("username");
    	String contactNo=request.getParameter("contactno");
    	String collegeName=request.getParameter("collegename");
    	String emailId=request.getParameter("emailid");
    	
    	 ModelAndView model = new ModelAndView();
         SymposiumDto symposiumDto=sympService.getSymposium(symposiumId);
         
         Symposium symposium = new Symposium(symposiumDto);
         
         
         String filename = file.getOriginalFilename();
			String id=UUID.randomUUID().toString();
			String Imagedirectorypath=filePath;
			File Imagedirectory = new File(filePath);
			if (!Imagedirectory.exists()) {
				if (Imagedirectory.mkdir()) {
					logger.debug("Directory created successfully");
				} else {
					logger.debug("Failed to create directory!");
				}
			}
			 int filesCount = FileUtils.getCount(Imagedirectorypath,null);
			 String name=FileUtils.getFilePath(filename, filesCount,null);
			 String finalpath=(Imagedirectorypath + "/"+ name);
			 String Linkpath="/"+"Papers"+"/"+name;
			
			 
			 if (!file.isEmpty()) {
		         try {
		             byte[] bytes = file.getBytes();
		             File finalImagepath = new File(finalpath);
		             BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(finalImagepath));
		             stream.write(bytes);
		             stream.close();
		             
		            
		         } catch (Exception e) {
		        	 System.out.println(e.getMessage());  
		         }
		     } 
			 Papers paper=new Papers();
			 paper.setSymposium(symposium);
			 paper.setFilePath(Linkpath);
			 paper.setCollegeName(collegeName);
			 paper.setContactNo(contactNo);
			 paper.setEmailId(emailId);
			 paper.setUserName(userName);
		     sympService.savePapers(paper);
         model.addObject("papersubmitted", true);
         model.setViewName("redirect:/symposiumview/" + symposiumId+"/"+symposium.getName());
         return model;
    }
    
    @GetMapping(value = "/symposiumpapers/{symposiumId}")
    public ModelAndView viewPapers(@PathVariable String symposiumId,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        String userid=(String) request.getSession().getAttribute("userId");
        model.addObject("symposiumname", sympService.getSymposiumName(userid));
        model.addObject("symposium", sympService.getSymposiumsBySymposiumId(userid, symposiumId));
        model.setViewName("symposiumpapers");	
        SocialUser socialUser=(SocialUser)request.getSession().getAttribute("user");
        if(socialUser!=null)
        {
        	model.addObject("authenticated", Boolean.TRUE);
        }
        return model;
        
    }
    @PostMapping(value = "/postcomment")
    public ModelAndView postComments(CommentsDto commentsDto,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	UserDetails socialUser=null;
    	if(checkSession()){
    	 socialUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	}
		
    	SymposiumDto symposiumDto=sympService.getSymposium(commentsDto.getSymposiumId());
        Symposium symposium = new Symposium(symposiumDto);

        if(socialUser!=null){
    	User user=(User) registrationUserDetailService.getUserById(socialUser.getUserId());

    	SymposiumComment symposiumComment=new SymposiumComment();
    	symposiumComment.setComment(commentsDto.getComment());
    	symposiumComment.setSymposium(symposium);
    	symposiumComment.setUser(user);
    	sympService.saveSymposiumComments(symposiumComment);
        }
    	ModelAndView model = new ModelAndView();
        model.addObject("title", symposium.getName());
        model.setViewName("redirect:/symposiumview/" + commentsDto.getSymposiumId()+"/"+symposium.getName());        
        return model;
        
    }
    
    
    @PostMapping(value = "/postreply")
    public ModelAndView postReply(SymposiumCommentsReplyDto symposiumCommentsReplyDto,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
       
    	UserDetails socialUser=null;
    	ModelAndView model = new ModelAndView();
    	if(checkSession()){
    	 socialUser = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	}
    	if(StringUtils.isEmpty(symposiumCommentsReplyDto.getCommentId())){
    		  model.setViewName("home");    
    		  return model;
    	}
		
        SymposiumCommentDto symposiumComment=sympService.getSymposiumComments(symposiumCommentsReplyDto.getCommentId());
        
        String symposiumName=symposiumComment.getSymposium();
        
        
        if(socialUser!=null){
    	User user=(User) registrationUserDetailService.getUserById(socialUser.getUserId());

        SymposiumCommentsReply symposiumCommentsReply=new SymposiumCommentsReply();
        symposiumCommentsReply.setPostedDate(new Date());
        symposiumCommentsReply.setReply(symposiumCommentsReplyDto.getReply());
        SymposiumComment comment=new SymposiumComment();
        
        comment.setId(symposiumComment.getId());
        
        symposiumCommentsReply.setSymposiumComment(comment);
        symposiumCommentsReply.setUser(user);
    	sympService.saveSymposiumCommentsReply(symposiumCommentsReply);
        }
    	
        model.addObject("title",symposiumName );
        model.setViewName("redirect:/symposiumview/" + symposiumComment.getSymposiumId()+"/"+symposiumName);        
        return model;
        
    }
    
	public boolean checkSession() {
		if (SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return true;
		}
		return false;
	}
	
}
