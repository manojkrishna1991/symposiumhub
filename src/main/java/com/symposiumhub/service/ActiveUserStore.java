package com.spring.security.social.login.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class ActiveUserStore {
	 


	public List<UserDetails> users;
 
    public List<UserDetails> getUsers() {
		return users;
	}

	public void setUsers(List<UserDetails> users) {
		this.users = users;
	}

	public ActiveUserStore() {
        users = new ArrayList<UserDetails>();
    }
 
    // standard getter and setter
}