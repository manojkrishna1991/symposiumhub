package com.symposiumhub.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.symposiumhub.model.*;

@Service(value = "profile")
public class ProfileComponent {

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertProfile;

	@Value("${imagepath}")
	private String imagePath;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertProfile = new SimpleJdbcInsert(dataSource).withTableName("profile");
	}

	public void add(Profile profile) {
		Map<String, Object> parameters = new HashMap<>(3);
		parameters.put("id", profile.getId());
		parameters.put("aboutMe", profile.getAboutMe());
		parameters.put("gender", profile.getGender());
		parameters.put("photo", profile.getPhoto());
		parameters.put("place", profile.getPlace());
		parameters.put("userId", profile.getUserId());

		insertProfile.execute(parameters);
	}

	public void update(Profile profile) {

		this.jdbcTemplate.update("update profile set aboutme = ? ,gender=?,photo=?,place=?,userId=? where id = ?",
				profile.getAboutMe(), profile.getGender(), profile.getPhoto(), profile.getPlace(), profile.getUserId(),
				profile.getId());

	}

	public List<Profile> getProfile(String userId) {

		List<Profile> actors = this.jdbcTemplate.query(
				"select id, aboutMe,gender,photo,place,userId,name from profile p inner join user u on u.USER_ID=p.userId  where p.userId=?  ", new Object[] { userId },
				new RowMapper<Profile>() {
					public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
						Profile profile = new Profile();
						profile.setId(Integer.parseInt(rs.getString("id")));
						profile.setAboutMe(rs.getString("aboutMe"));
						profile.setGender(rs.getString("gender"));
						profile.setPhoto(rs.getString("photo"));
						profile.setPlace(rs.getString("place"));
						profile.setUserId(rs.getString("userId"));
						profile.setName(rs.getString("name"));
						return profile;
					}

				});
		return actors;
	}

	public List<Profile> getProfileByProfileId(Integer profileId) {

		List<Profile> actors = this.jdbcTemplate.query(
				"select p.id, aboutMe,gender,photo,place,p.userId,u.NAME from profile p inner join user u on u.USER_ID=p.userId  where id=?   ",
				new Object[] { profileId }, new RowMapper<Profile>() {
					public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
						Profile profile = new Profile();
						profile.setId(Integer.parseInt(rs.getString("id")));
						profile.setAboutMe(rs.getString("aboutMe"));
						profile.setGender(rs.getString("gender"));
						profile.setPhoto(rs.getString("photo"));
						profile.setPlace(rs.getString("place"));
						profile.setUserId(rs.getString("userId"));
						profile.setName(rs.getString("name"));
						return profile;
					}

				});
		return actors;
	}

	public List<Profile> getProfileToMakeFreinds(String userId, Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(
				"select id, aboutMe,gender,photo,place,userId,name from profile ps  inner join user u on u.USER_ID=ps.userId where ps.userId  !='" + userId + "' ");
		stringBuilder.append("and  ps.id not in ( ");
		stringBuilder.append("SELECT  P.id ");
		stringBuilder.append("FROM profile P, friends F ");
		stringBuilder.append("WHERE ");
		stringBuilder.append("CASE ");
		stringBuilder.append("WHEN F.toprofileid =" + profileId + " ");
		stringBuilder.append("THEN F.fromprofileid = P.id ");
		stringBuilder.append("WHEN F.fromprofileid=" + profileId + " ");
		stringBuilder.append("THEN F.toprofileid= P.id ");
		stringBuilder.append("END ");
		stringBuilder.append(")");
		List<Profile> actors = this.jdbcTemplate.query(stringBuilder.toString(), new RowMapper<Profile>() {
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				Profile profile = new Profile();
				profile.setId(Integer.parseInt(rs.getString("id")));
				profile.setAboutMe(rs.getString("aboutMe"));
				profile.setGender(rs.getString("gender"));
				profile.setPhoto(rs.getString("photo"));
				profile.setPlace(rs.getString("place"));
				profile.setUserId(rs.getString("userId"));
				profile.setName(rs.getString("name"));
				return profile;
			}

		});
		return actors;
	}

	public List<Profile> getMyFreinds(Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("select P.id, aboutMe,gender,photo,place,userId,name from profile P inner join user u on u.USER_ID=P.userId , friends F   ");
		stringBuilder.append("WHERE ");
		stringBuilder.append("CASE ");
		stringBuilder.append("WHEN F.toprofileid =" + profileId + " ");
		stringBuilder.append("THEN F.fromprofileid = P.id ");
		stringBuilder.append("WHEN F.fromprofileid=" + profileId + " ");
		stringBuilder.append("THEN F.toprofileid= P.id ");
		stringBuilder.append("END ");
		stringBuilder.append("AND ");
		stringBuilder.append("F.relationstatus='1'");
		List<Profile> actors = this.jdbcTemplate.query(stringBuilder.toString(), new RowMapper<Profile>() {
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				Profile profile = new Profile();
				profile.setId(Integer.parseInt(rs.getString("id")));
				profile.setAboutMe(rs.getString("aboutMe"));
				profile.setGender(rs.getString("gender"));
				profile.setPhoto(rs.getString("photo"));
				profile.setPlace(rs.getString("place"));
				profile.setUserId(rs.getString("userId"));
				profile.setName(rs.getString("name"));

				return profile;
			}

		});
		return actors;
	}

	public void insertFreinds(Integer profileId, Integer freindId) {

		this.jdbcTemplate.update("insert into friends (toprofileid,fromprofileid) values (?,?)", profileId, freindId);

	}

	public void updateFreinds(Integer profileId, Integer freindId) {

		this.jdbcTemplate.update(
				"update friends SET relationstatus=" + 1 + " WHERE (toprofileid=" + profileId + " OR fromprofileid="
						+ profileId + ") " + " AND  (toprofileid=" + freindId + " OR fromprofileid=" + freindId + ")");

	}

	public List<Profile> getRequestedFreinds(Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("select P.id, aboutMe,gender,photo,place,userId,name from profile P inner join user u on u.USER_ID=P.userId, friends F  ");
		stringBuilder.append("WHERE ");
		stringBuilder.append("CASE ");
		stringBuilder.append("WHEN F.fromprofileid=" + profileId + " ");
		stringBuilder.append("THEN F.toprofileid= P.id ");
		stringBuilder.append("END ");
		stringBuilder.append("AND ");
		stringBuilder.append("F.relationstatus is null");
		List<Profile> actors = this.jdbcTemplate.query(stringBuilder.toString(), new RowMapper<Profile>() {
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				Profile profile = new Profile();
				profile.setId(Integer.parseInt(rs.getString("id")));
				profile.setAboutMe(rs.getString("aboutMe"));
				profile.setGender(rs.getString("gender"));
				profile.setPhoto(rs.getString("photo"));
				profile.setPlace(rs.getString("place"));
				profile.setUserId(rs.getString("userId"));
				profile.setName(rs.getString("name"));

				return profile;
			}

		});
		return actors;
	}

	public List<Profile> getFreindsActivity(String userId, Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("select u.name ,D.message,D.created,p.id ,photo,place,p.userId from updates D ");
		stringBuilder.append(" inner join profile  p on D.profile_id_fk=p.id  inner join   ");
		stringBuilder.append(" (select toprofileid from friends fs1 where fs1.fromprofileId=" + profileId + " and fs1.relationstatus>0 ");
		stringBuilder.append(" union ");
		stringBuilder.append(" select fromprofileId from friends fs2 where fs2.toprofileid=" + profileId + " and  fs2.relationstatus>0 ");
		stringBuilder.append(" union select " + profileId + " ) ");
		stringBuilder.append(" as friendsId on D.profile_id_fk= friendsId.toprofileid  ");
		stringBuilder.append(" inner join user u  on u.user_Id=p.userId ");
		stringBuilder.append(" ORDER BY D.update_id DESC");
		List<Profile> actors = this.jdbcTemplate.query(stringBuilder.toString(), new RowMapper<Profile>() {
			public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
				Profile profile = new Profile();
				profile.setName(rs.getString("name"));
				profile.setId(Integer.parseInt(rs.getString("id")));
				profile.setUpdate(rs.getString("message"));
				profile.setPhoto(rs.getString("photo"));
				profile.setPlace(rs.getString("place"));
				profile.setUserId(rs.getString("userId"));
				return profile;
			}

		});
		return actors;
	}

	public void insertActivity(String message, Integer profileId) {

		this.jdbcTemplate.update("insert into updates (message,profile_id_fk) values (?,?)", message, profileId);

	}
	
	public List<Email> getAllEmailId(){
		StringBuilder query=new StringBuilder();

		query.append("select distinct email from subscribe");
		query.append("union");
		query.append("select distinct email_id from user");
		query.append("union");
		query.append("select distinct emailid from coordinator");
		
		List<Email> actors = this.jdbcTemplate.query(query.toString(), new RowMapper<Email>() {
			public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
				Email actor = new Email();
				actor.setEmailId(rs.getString(1));
				
				
				return actor;
			}
		});
		
		return actors;
		
	}

}
