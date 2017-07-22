package com.symposiumhub.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.symposiumhub.datasource.EventRepositoryComponent;
import com.symposiumhub.datasource.RegisterForEventComponent;
import com.symposiumhub.email.EmailQueue;
import com.symposiumhub.model.Registration;

@Service
@EnableScheduling
public class RegistrationScheduler {

	static Logger log = Logger.getLogger(RegistrationScheduler.class.getName());

	@Autowired
	private EventRepositoryComponent eventRepository;

	@Autowired
	private RegisterForEventComponent registerForEventComponet;

	@Autowired
	private EmailQueue queue;

	@Scheduled(cron = "0 0 3 * * ?")
	public void demoServiceMethod() {

		try {
			List<Registration> validSymposiumRegistrations = registerForEventComponet.getRegistrations();
			for (Registration registration : validSymposiumRegistrations) {
				queue.sendSymposiumRegistrationEmail(registration.getSendEmailTo(), registration.getName(),
						String.valueOf(registration.getRegCount()));
				registerForEventComponet.updateStatusToIsMailSent(registration.getId());
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage() + new Date());
			return;
		}

	}

}
