package com.symposiumhub.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.symposiumhub.dto.UserRegistrationForm;
import com.symposiumhub.exception.UserAlreadyExistAuthenticationException;
import com.symposiumhub.model.User;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 28/3/16
 */
public interface UserService {

    public UserDetails registerNewUser(UserRegistrationForm UserRegistrationForm)throws UserAlreadyExistAuthenticationException;
   
    public User getUserById(String userId);
    
    public User SaveorUpdateUser(User user);

}
