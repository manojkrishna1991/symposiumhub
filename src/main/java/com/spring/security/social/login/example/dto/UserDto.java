package com.spring.security.social.login.example.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.spring.security.social.login.example.database.model.Role;
import com.spring.security.social.login.example.database.model.User;

/**
 * @author <a href="mailto:psunil1278@gmail.com">Sunil Kumar</a>
 * @since 25/12/15
 */

public class UserDto {

    private String userId;

    private String name;

  
    private String password;

    private String emailId;

    private Integer active;

    private String provider;
    
  

    private Set<Role> roles = new HashSet<>();

    public UserDto() {
    }

    public UserDto(final String userId, final String name, final String password, final String emailId, final Integer active, final String provider, final Set<Role> roles) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.emailId = emailId;
        this.active = active;
        this.provider = provider;
        this.roles = roles;
    }
    
    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.emailId = user.getEmailId();
        this.active = user.getActive();
        this.provider = user.getProvider();
        this.roles = user.getRoles();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(final Integer active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(final String provider) {
        this.provider = provider;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }
}
