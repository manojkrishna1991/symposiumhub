package com.spring.security.social.login.example.service;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoggedUser implements HttpSessionBindingListener {
 
    private UserDetails user;
    
    
    
 
	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public ActiveUserStore getActiveUserStore() {
		return activeUserStore;
	}

	public void setActiveUserStore(ActiveUserStore activeUserStore) {
		this.activeUserStore = activeUserStore;
	}

	private ActiveUserStore activeUserStore;
     
    public LoggedUser(UserDetails user, ActiveUserStore activeUserStore) {
        this.user = user;
        this.activeUserStore = activeUserStore;
    }
     
    public LoggedUser() {}
 
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<UserDetails> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getUser())) {
            users.add(user.getUser());
        }
    }
 
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<UserDetails> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(user.getUser())) {
            users.remove(user.getUser());
        }
    }
 
    // standard getter and setter
}