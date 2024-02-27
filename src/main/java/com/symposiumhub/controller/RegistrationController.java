package com.symposiumhub.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.symposiumhub.dto.LocalUser;
import com.symposiumhub.dto.UserRegistrationForm;
import com.symposiumhub.exception.UserAlreadyExistAuthenticationException;
import com.symposiumhub.service.SymposiumServiceInterface;
import com.symposiumhub.service.UserService;
import com.symposiumhub.util.SecurityUtil;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 29/3/16
 */
@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
	private SymposiumServiceInterface  sympService;

    @GetMapping(value = "/signup")
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "User Registration Form");
        model.setViewName("userRegistration");
        return model;
    }

    @PostMapping(value = {"/user/register"})
    public @ResponseBody ModelAndView registerUser(UserRegistrationForm registrationForm,HttpServletRequest request,HttpServletResponse response) throws UserAlreadyExistAuthenticationException, IOException {

        if (registrationForm.getUserId() == null) {
            registrationForm.setUserId(registrationForm.getUserId());
        }
        LocalUser localUser=null;
        ModelAndView model = new ModelAndView();

        try{
        	localUser = (LocalUser) userService.registerNewUser(registrationForm);
        }
        catch(UserAlreadyExistAuthenticationException e){
        	e.getMessage();
            model.setViewName("redirect:/signup?message=username already exists");;
            return model;
        }
        SecurityUtil.authenticateUser(localUser);

        model.addObject("title", "SymposiumHub Welcome ");
        model.addObject("user", localUser.getUsername());
        String userid=(String) request.getSession().getAttribute("userId");
        model.addObject("symposium", sympService.getSymposiumByUserId(userid));
        model.addObject("symposiumname", sympService.getSymposiumName(userid));
        model.setViewName("redirect:/dashboard");	
        return model;

    }
    
}
