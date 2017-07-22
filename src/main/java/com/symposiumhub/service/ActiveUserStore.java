package com.symposiumhub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.symposiumhub.model.User;


@Service
public class ActiveUserStore {
	 


	public List<User> users;
 
    public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public ActiveUserStore() {
        users = new ArrayList<User>();
    }
 
    // standard getter and setter
}