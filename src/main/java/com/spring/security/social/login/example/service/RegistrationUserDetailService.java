package com.spring.security.social.login.example.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.security.social.login.example.database.dao.UserDAO;
import com.spring.security.social.login.example.database.model.Role;
import com.spring.security.social.login.example.database.model.User;
import com.spring.security.social.login.example.dto.LocalUser;
import com.spring.security.social.login.example.dto.UserRegistrationForm;
import com.spring.security.social.login.example.email.EmailQueue;
import com.spring.security.social.login.example.exception.UserAlreadyExistAuthenticationException;

/**
 * @author <a href="mailto:sunil.pulugula@wavemaker.com">Sunil Kumar</a>
 * @since 28/3/16
 */
@Service("registrationUserDetailService")
public class RegistrationUserDetailService implements UserService {

    @Autowired
    @Qualifier(value = "localUserDetailService")
    private UserDetailsService userDetailService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EmailQueue queue;
    
    @Override
    @Transactional(value = "transactionManager")
    public LocalUser registerNewUser(final UserRegistrationForm userRegistrationForm) throws UserAlreadyExistAuthenticationException {

        com.spring.security.social.login.example.database.model.User userExist = userDAO.get(userRegistrationForm.getUserId());
        if (userExist != null) {
        	  com.spring.security.social.login.example.database.model.User user = buildUser(userRegistrationForm);
        	  return (LocalUser) userDetailService.loadUserByUsername(userRegistrationForm.getUserId());
           
        }
          
        com.spring.security.social.login.example.database.model.User user = buildUser(userRegistrationForm);
        userDAO.save(user);
        userDAO.flush();
        //registration welcome emails check for email
        if(!user.getEmailId().isEmpty()){
        queue.sendRegistrationEmail(user.getEmailId(),user.getName());
        }

        return (LocalUser) userDetailService.loadUserByUsername(userRegistrationForm.getUserId());
    }

    private User buildUser(final UserRegistrationForm formDTO) {
        User user = new User();
        user.setUserId(formDTO.getUserId());
        user.setEmailId(formDTO.getEmail());
        user.setName(formDTO.getFirstName());
        user.setPassword(formDTO.getPassword());
        final HashSet<Role> roles = new HashSet<Role>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        user.setActive(1);
        user.setProvider(formDTO.getSocialProvider().name());
        return user;
    }
}
