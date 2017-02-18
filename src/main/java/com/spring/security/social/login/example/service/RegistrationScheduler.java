package com.spring.security.social.login.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.security.social.login.example.email.EmailQueue;

@Service
@EnableScheduling
public class RegistrationScheduler  {

	static Logger log = Logger.getLogger(RegistrationScheduler.class.getName());


    @Autowired
    private HibernateTemplate template;
    
    @Autowired
  	private SymposiumServiceInterface  sympService;

	@Autowired
	private EmailQueue queue;



	@Scheduled(cron = "0 0 3 * * ?")
	public void demoServiceMethod() {

		try {
			List<Object[]> validSymposiumRegistrations = sympService.getValidSymposiumRegistrations();
			List<String> list=new ArrayList<>();
			for (Object[] objects : validSymposiumRegistrations) {
				queue.sendSymposiumRegistrationEmail(objects[2].toString(), objects[1].toString(),
						objects[0].toString());
				list.add(objects[3].toString());
			}
			
			sympService.updateStatusToIsMailSent(list);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage() + new Date());
			log.info(e.getMessage() + new Date());
			return ;
		}

	}


}
