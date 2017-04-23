package com.spring.security.social.login.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.spring.security.social.login.example.database.model.Greeting;
import com.spring.security.social.login.example.database.model.HelloMessage;




@ComponentScan
@Controller
public class GreetingController {

	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public GreetingController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	// ...

	public void afterTradeExecuted(HelloMessage trade) {
		messagingTemplate.convertAndSend("/topic/greetings/"+trade.getRoomName(), new Greeting(trade.getUserName(),trade.getName()));

	}
   
	
	

}
