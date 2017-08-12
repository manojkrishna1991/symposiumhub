package com.symposiumhub.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.text.DefaultEditorKit.InsertContentAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.symposiumhub.model.College;
import com.symposiumhub.model.Coordinator;
import com.symposiumhub.model.GenericEvent;
import com.symposiumhub.model.GenericEventRegistrationFields;
import com.symposiumhub.model.Profile;

@Service
public class EventRepositoryComponent extends BaseRepositoryComponent {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertProfile;
	private SimpleJdbcInsert InsertCoordinator;
	private SimpleJdbcInsert InsertRegistration;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertProfile = new SimpleJdbcInsert(dataSource).withTableName("genericevent")
				.usingGeneratedKeyColumns("eventid");
		;
		this.InsertCoordinator = new SimpleJdbcInsert(dataSource).withTableName("eventcoordinator");
		this.InsertRegistration = new SimpleJdbcInsert(dataSource).withTableName("event_registration");
	}

	public void add(GenericEvent event) {
		Map<String, Object> parameters = new HashMap<String, Object>(3);

		parameters.put("name", event.getName());
		parameters.put("dateOfEvent", event.getDateOfEvent());
		parameters.put("regEmail", event.getRegEmail());
		parameters.put("content", event.getContent());
		parameters.put("createDate", new Date());
		parameters.put("eventType", event.getEventType());
		parameters.put("department", event.getDepartment());
		parameters.put("organizationName", event.getOrganizationName());
		parameters.put("paymentDetail", event.getPaymentDetail());
		parameters.put("userId", event.getUserId());
		parameters.put("imageUrl", event.getImageUrl());
		Number newId = insertProfile.executeAndReturnKey(parameters);
		if (newId != null) {
			event.setEventid(String.valueOf(newId));
		}

		for (Coordinator coordinator : event.getCoordinatorsAsList()) {
			Map<String, Object> coordinators = new HashMap<String, Object>();
			if (coordinator.isValid()) {
				coordinators.put("name", coordinator.getName());
				coordinators.put("emailid", coordinator.getEmailid());
				coordinators.put("phoneno", coordinator.getPhoneno());
				coordinators.put("eventid", newId.longValue());
				InsertCoordinator.execute(coordinators);
			}
		}

	}

	public void addRegistrationFields(GenericEvent event) {
		GenericEventRegistrationFields genericEventRegistrationFields = event.getFields();
		if(genericEventRegistrationFields==null){
			genericEventRegistrationFields=new GenericEventRegistrationFields();
		}
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("fullName", genericEventRegistrationFields.getFullName());
		parameters.put("phoneNumber", genericEventRegistrationFields.getPhoneNumber());
		parameters.put("email", genericEventRegistrationFields.getEmail());
		parameters.put("collegeName", genericEventRegistrationFields.getCollegeName());
		parameters.put("collegeId", genericEventRegistrationFields.getCollegeId());
		parameters.put("eventid", event.getEventid());
		InsertRegistration.execute(parameters);

	}

	public GenericEvent findEventById(int custId) {
		try {
			String sql = "SELECT * FROM genericevent WHERE eventid = ?";

			GenericEvent event = (GenericEvent) jdbcTemplate.queryForObject(sql, new Object[] { custId },
					new BeanPropertyRowMapper(GenericEvent.class));

			return event;
		} catch (Exception e) {
			return null;
		}
	}

	public GenericEvent findEventByUserId(int eventId, String userId) {
		try {
			String sql = "SELECT * FROM genericevent WHERE eventid = ?";

			GenericEvent event = (GenericEvent) jdbcTemplate.queryForObject(sql, new Object[] { eventId },
					new BeanPropertyRowMapper(GenericEvent.class));

			return event;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Coordinator> findCoordinatorById(Integer id) {

		List<Coordinator> actors = this.jdbcTemplate.query(
				"select eventid,name,emailid,phoneno  from eventcoordinator where eventid=?", new Object[] { id },
				new RowMapper<Coordinator>() {
					public Coordinator mapRow(ResultSet rs, int rowNum) throws SQLException {
						Coordinator coordinator = new Coordinator();
						coordinator.setId(Integer.parseInt(rs.getString("eventid")));
						coordinator.setName(rs.getString("name"));
						coordinator.setEmailid(rs.getString("emailid"));
						coordinator.setPhoneno(rs.getString("phoneno"));

						return coordinator;
					}

				});
		return actors;
	}

	public boolean updateEvent(GenericEvent event) {

		this.jdbcTemplate.update(
				"update genericevent set  name= ? ,dateOfEvent=?,department=?,organizationName=?,regEmail=?,content=?,paymentDetail=?,imageUrl=?   where eventid = ?",
				event.getName(), event.getDateOfEvent(), event.getDepartment(), event.getOrganizationName(),
				event.getRegEmail(), event.getContent(), event.getPaymentDetail(),event.getImageUrl(),event.getEventid());
		return true;

	}

	public boolean updateCoordinatorEvent(List<Coordinator> list, GenericEvent event) {

		for (Coordinator coordinator : list) {
			if (!StringUtils.isEmpty(coordinator.getId())) {
				this.jdbcTemplate.update("update eventcoordinator set  name= ? ,emailid=?,phoneno=?  where eventid = ?",
						coordinator.getName(), coordinator.getEmailid(), coordinator.getPhoneno(), coordinator.getId());
			}
			if (StringUtils.isEmpty(coordinator.getId()) && coordinator.isValid()) {
				Map<String, Object> coordinators = new HashMap<String, Object>();
				coordinators.put("name", coordinator.getName());
				coordinators.put("emailid", coordinator.getEmailid());
				coordinators.put("phoneno", coordinator.getPhoneno());
				coordinators.put("eventid", Integer.parseInt(event.getEventid()));
				InsertCoordinator.execute(coordinators);
			}
		}

		return true;
	}

	public List<GenericEvent> getAllEvent(String eventType) {
		final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		String SqlQuery = "select eventid,name,dateOfEvent,department,organizationName,imageUrl from genericevent where eventType='"
				+ eventType + "' order by dateofEvent DESC";
		if ("all".equalsIgnoreCase(eventType)) {
			SqlQuery = "select eventid,name,dateOfEvent,department,organizationName,imageUrl from genericevent  order by dateofEvent DESC";

		}
		return this.jdbcTemplate.query(SqlQuery, new RowMapper<GenericEvent>() {
			public GenericEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
				GenericEvent event = new GenericEvent();
				event.setEventid(rs.getString(1));
				event.setName(rs.getString(2));
				event.setDepartment(rs.getString(4));
				event.setOrganizationName(rs.getString(5));
				event.setImageUrl(rs.getString(6));
				
				try {
					Date date = inputFormat.parse(rs.getString(3));
					event.setDateOfEvent(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return event;
			}
		});

	}

	public List<GenericEvent> getEventByUserId(String Id) {
		final DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

		String SqlQuery = "select eventid,name,dateOfEvent,department,organizationName from genericevent where userId='"
				+ Id + "' order by dateofEvent DESC";

		List<GenericEvent> actors = this.jdbcTemplate.query(SqlQuery, new RowMapper<GenericEvent>() {
			public GenericEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
				GenericEvent actor = new GenericEvent();
				actor.setEventid(rs.getString(1));
				actor.setName(rs.getString(2));
				actor.setDepartment(rs.getString(4));
				actor.setOrganizationName(rs.getString(5));
				try {
					Date date = inputFormat.parse(rs.getString(3));
					actor.setDateOfEvent(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return actor;
			}
		});

		return actors;
	}

}
