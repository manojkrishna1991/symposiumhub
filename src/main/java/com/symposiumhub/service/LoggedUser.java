package com.symposiumhub.service;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.symposiumhub.model.User;

@Component
public class LoggedUser implements HttpSessionBindingListener {
 
    private User user;
    
    
    
 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ActiveUserStore getActiveUserStore() {
		return activeUserStore;
	}

	public void setActiveUserStore(ActiveUserStore activeUserStore) {
		this.activeUserStore = activeUserStore;
	}

	private ActiveUserStore activeUserStore;
     
    public LoggedUser(User user, ActiveUserStore activeUserStore) {
        this.user = user;
        this.activeUserStore = activeUserStore;
    }
     
    public LoggedUser() {}
 
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<User> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getUser())) {
            users.add(user.getUser());
        }
    }
 
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<User> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (user!=null && users.contains(user.getUser())) {
        	users.remove(user.getUser());
        }
    }
 
    // standard getter and setter
}