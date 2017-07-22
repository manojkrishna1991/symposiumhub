package com.symposiumhub.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.symposiumhub.database.dao.UserDAO;
import com.symposiumhub.dto.LocalUser;
import com.symposiumhub.dto.UserRegistrationForm;
import com.symposiumhub.email.EmailQueue;
import com.symposiumhub.exception.UserAlreadyExistAuthenticationException;
import com.symposiumhub.model.Role;
import com.symposiumhub.model.User;

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

	private User user2;
    
    @Override
    @Transactional(value = "transactionManager")
    public LocalUser registerNewUser(final UserRegistrationForm userRegistrationForm) throws UserAlreadyExistAuthenticationException {

        com.symposiumhub.model.User userExist = userDAO.get(userRegistrationForm.getUserId());
        if (userExist != null && !userExist.getProvider().equalsIgnoreCase("NONE")) {
        	  com.symposiumhub.model.User user = buildUser(userRegistrationForm);
        	  return (LocalUser) userDetailService.loadUserByUsername(userRegistrationForm.getUserId());
           
        }
        if (userExist != null && userExist.getProvider().equalsIgnoreCase("NONE")) {
      	  
        	throw new UserAlreadyExistAuthenticationException("username already exists");
         
      }
        	
          
        com.symposiumhub.model.User user = buildUser(userRegistrationForm);
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

	@Override
	public User getUserById(String userId) {
		// TODO Auto-generated method stub
		return userDAO.get(userId);
		
	}

	@Override
	@Transactional	
	public User SaveorUpdateUser(User user) {
		// TODO Auto-generated method stub
		
		if(user.getUserId()==null){
			return null;
		}
		
		User userFromDataBase = userDAO.get(user.getUserId());
		
		userFromDataBase.setPassword(user.getPassword() != null && 
				!user.getPassword().equals(userFromDataBase.getPassword()) ? 
				user.getPassword():userFromDataBase.getPassword());
		
		userFromDataBase.setActivationKey(user.getActivationKey() != null && 
				!user.getActivationKey().equals(userFromDataBase.getActivationKey()) ? 
				user.getActivationKey():userFromDataBase.getActivationKey());
		
		userDAO.saveOrUpdate(userFromDataBase);
		
		return userFromDataBase;
	}
}
