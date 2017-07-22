package com.symposiumhub.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.symposiumhub.model.Notification;
import com.symposiumhub.model.Profile;

@Service
public class NotificationComponent {

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertProfile;

	private SimpleJdbcInsert insertNotificationLastViewed;

	@Value("${imagepath}")
	private String imagePath;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertProfile = new SimpleJdbcInsert(dataSource).withTableName("notification");
		this.insertNotificationLastViewed = new SimpleJdbcInsert(dataSource).withTableName("notificationLastViewed");
	}

	public void add(Notification notification) {
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("notification", notification.getNotification());
		parameters.put("Type", notification.getType());
		parameters.put("toprofileid", notification.getToprofileid());
		parameters.put("fromprofileid", notification.getFromprofileid());
		insertProfile.execute(parameters);
	}

	public Integer getNotificationCount(Integer profileid) {

		if (getNotificationLastViewedCount(profileid) == 0) {
			
			insertNotificationLastViewed(profileid);
			
		}

		Integer rowCount = this.jdbcTemplate.queryForObject(
				"select count(*) from notification n where updatedDate >  (select v.NotificationLastViewed from  notificationLastViewed v where profileid="
						+ profileid + ") and  n.fromprofileid !=" + profileid + " order by n.updatedDate desc ",
				Integer.class);

		return rowCount;

	}

	public List<Notification> getNotification() {

		List<Notification> notification = this.jdbcTemplate.query(
				"select id,notification,Type,updatedDate from notification order by updatedDate desc  ",
				new RowMapper<Notification>() {
					public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
						Notification notification = new Notification();
						notification.setNotification(rs.getString("notification"));
						notification.setType(rs.getString("Type"));
						notification.setId(Integer.parseInt(rs.getString("id")));
						return notification;
					}

				});
		return notification;
	}

	public void update(Integer id) {

		this.jdbcTemplate.update("update notificationLastViewed set NotificationLastViewed = ? where profileid = ?",
				new Date(), id);

	}

	public void insertNotificationLastViewed(Integer profileid) {

		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("profileid", profileid);

		insertNotificationLastViewed.execute(parameters);
	}

	public Integer getNotificationLastViewedCount(Integer profileid) {

		Integer rowCount = this.jdbcTemplate
				.queryForObject("select count(*) from notificationLastViewed n where  n.profileid =" + profileid
						+ " order by n.NotificationLastViewed desc ", Integer.class);
		return rowCount;

	}

}
