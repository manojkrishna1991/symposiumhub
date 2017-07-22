package com.symposiumhub.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.symposiumhub.model.Greeting;
import com.symposiumhub.model.HelloMessage;




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
