package com.symposiumhub.datasource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.symposiumhub.model.GenericEvent;
import com.symposiumhub.model.GenericEventRegistrationFields;
import com.symposiumhub.model.Registration;

@Service
public class RegisterForEventComponent extends BaseRepositoryComponent {
	
	private static final Log logger = LogFactory.getLog(RegisterForEventComponent.class);


	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertProfile;

	private SimpleJdbcInsert register;
	
	@Autowired
	private EventRepositoryComponent eventRepository;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.register = new SimpleJdbcInsert(dataSource).withTableName("registrations");

	}

	public void add(Registration registration) {
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("fullName", registration.getFullName());
		parameters.put("collegeId", registration.getCollegeId());
		parameters.put("collegeName", registration.getCollegeName());
		parameters.put("email", registration.getEmail());
		parameters.put("phoneNumber", registration.getPhoneNumber());
		parameters.put("registerDate", new Date());
		parameters.put("eventid", registration.getEventid());
		Number newId = register.execute(parameters);

	}

	public GenericEventRegistrationFields findRegistrationFieldsById(Integer eventid) {
		try {
			String sql = "SELECT * FROM event_registration WHERE eventid = ?";

			GenericEventRegistrationFields registrationFields = (GenericEventRegistrationFields) jdbcTemplate
					.queryForObject(sql, new Object[] { eventid },
							new BeanPropertyRowMapper(GenericEventRegistrationFields.class));

			return registrationFields;
		} catch (DataAccessException e) {
			return null;
		}
	}

	public List<Registration> getRegistrations(Integer eventId) {

		try {
			String sql = "SELECT * FROM registrations WHERE eventid = ? order by registerDate DESC";
			List<Registration> registrationFields = (List<Registration>) jdbcTemplate.query(sql,
					new Object[] { eventId },new BeanPropertyRowMapper<Registration> (Registration.class));

			return registrationFields;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return null;
		}

	}
	
	public List<Registration> getRegistrations() {

		try {
			String sql = "SELECT reg.eventid as eventid,count(reg.id) as regCount,ge.regEmail as sendEmailTo,ge.name  FROM registrations reg left join genericevent ge on reg.eventid=ge.eventid WHERE (isMailSent is null or isMailSent=0) group by reg.eventid ";
			List<Registration> registrationFields = (List<Registration>) jdbcTemplate.query(sql,new BeanPropertyRowMapper<Registration> (Registration.class));

			return registrationFields;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return null;
		}

	}
	
	public boolean updateRegistrationFields(GenericEventRegistrationFields registration) {
		

		
		if(StringUtils.isEmpty(registration.getId())){
			GenericEvent event=new GenericEvent();
			event.setEventid(registration.getEventId());
			event.setFields(registration);
			eventRepository.addRegistrationFields(event);
			return false;
		}

		this.jdbcTemplate.update("update event_registration set  collegeId= ? ,collegeName=?,email=?,fullName=?,phoneNumber=?,registrationDate=?   where eventid = ?",
				registration.getCollegeId(),registration.getCollegeName(),registration.getEmail(),registration.getFullName(),registration.getPhoneNumber(),new Date(),registration.getEventId());
		return true;
		
	}

	public void updateStatusToIsMailSent(Integer id) {
		// TODO Auto-generated method stub
		try{
			this.jdbcTemplate.update("update registrations set  isMailSent=?   where id = ?",id);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
					
	}
}
