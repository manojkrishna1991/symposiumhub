package com.spring.security.social.login.example.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.spring.security.social.login.example.database.model.User;
import com.spring.security.social.login.example.dto.UserRegistrationForm;
import com.spring.security.social.login.example.exception.UserAlreadyExistAuthenticationException;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 28/3/16
 */
public interface UserService {

    public UserDetails registerNewUser(UserRegistrationForm UserRegistrationForm)throws UserAlreadyExistAuthenticationException;
   
    public User getUserById(String userId);

}
