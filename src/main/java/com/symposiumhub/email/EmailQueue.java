package com.symposiumhub.email;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;

import com.symposiumhub.util.EmailUtils;

@Component("emailQueue")
public class EmailQueue {

	 private BlockingQueue<EmailUtils> queueData = new LinkedBlockingDeque<EmailUtils>();
	 
	 private ExecutorService executorService;
	 
			 
	 public EmailQueue() {

	     
	        executorService = Executors.newSingleThreadExecutor();
	    }
	 
	 public void sendRegistrationEmail(String to,String userName){
		 
		 EmailUtils email=new EmailUtils(null,to,null);
		 email.setSenderName(userName);
		 email.setType("welcome");
		 queueData.add(email);
		 executorService.execute(new EmailConsumer());
	 }
	 
	 public void sendSymposiumRegistrationEmail(String to,String userName,String no){
		 EmailUtils email=new EmailUtils(null,to,null);
		 email.setSenderName(userName);
		 email.setType("symreg");
		 Map<String,String> paramters=new HashMap<>();
		 paramters.put("no", no);
		 email.setParameters(paramters);
		 queueData.add(email);
		 executorService.execute(new EmailConsumer());
	 }
	 
	 public void sendResetPassword(String to,String userName,String forgetPasswordLink){
		 EmailUtils email=new EmailUtils(null,to,null);
		 email.setSenderName(userName);
		 email.setType("reset");
		 Map<String,String> paramters=new HashMap<>();
		 paramters.put("link", forgetPasswordLink);
		 email.setParameters(paramters);
		 queueData.add(email);
		 executorService.execute(new EmailConsumer());
	 }
	 
	 
	 
	 public class EmailConsumer implements Runnable{

		 
		     @Override
		     public void run() {
		         try{
		        	 EmailUtils msg;
		             //consuming messages until exit message is received
		        
		        	 msg = queueData.take();
		        	 if("welcome".equals(msg.getType())){
		        	 msg.sendWelcomeEmail();
		        	 }
		        	 if("symreg".equals(msg.getType())){
		        		 Map<String,String> map=msg.getParameters();
		        		 String no=map.get("no");
		        		 msg.sendSymposiumRegistrationEmail(no);
		        	 }
		        	 if("reset".equals(msg.getType())){
		        		 Map<String,String> map=msg.getParameters();
		        		 String link=map.get("link");
		        		 msg.sendForgetPasswordLink(link);
		        	 }
		        	 
		             
		         }catch(InterruptedException e) {
		             e.printStackTrace();
		         }
		     }
		 }
	 
	 
	
			
			
			
		
}
