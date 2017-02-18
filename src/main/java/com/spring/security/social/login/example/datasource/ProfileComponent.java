package com.spring.security.social.login.example.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.spring.security.social.login.example.database.model.*;

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
		Map<String, Object> parameters = new HashMap<String, Object>(3);
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
				"select id, aboutMe,gender,photo,place,userId from profile where userId=?  ", new Object[] { userId },
				new RowMapper<Profile>() {
					public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
						Profile profile = new Profile();
						profile.setId(Integer.parseInt(rs.getString("id")));
						profile.setAboutMe(rs.getString("aboutMe"));
						profile.setGender(rs.getString("gender"));
						profile.setPhoto(rs.getString("photo"));
						profile.setPlace(rs.getString("place"));
						profile.setUserId(rs.getString("userId"));
						return profile;
					}

				});
		return actors;
	}

	
	
	public List<Profile> getProfileByProfileId(Integer profileId) {

		List<Profile> actors = this.jdbcTemplate.query(
				"select p.id, aboutMe,gender,photo,place,p.userId,u.NAME from profile p,User u where id=? and p.userId=u.USER_ID  ", new Object[] { profileId },
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
	
	
	
	public List<Profile> getProfileToMakeFreinds(String userId, Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(
				"select id, aboutMe,gender,photo,place,userId from profile ps where ps.userId  !='"+userId+"' ");
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
				return profile;
			}

		});
		return actors;
	}
	
	
	public List<Profile> getMyFreinds( Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(
				"select P.id, aboutMe,gender,photo,place,userId from profile P, friends F ");
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
				return profile;
			}

		});
		return actors;
	}
	
	
	

	public void insertFreinds(Integer profileId, Integer freindId) {

		this.jdbcTemplate.update("insert into friends (toprofileid,fromprofileid) values (?,?)",profileId,freindId);

	}

	public void updateFreinds(Integer profileId, Integer freindId) {
		
		this.jdbcTemplate.update("update friends SET relationstatus="+1+" WHERE (toprofileid="+profileId+" OR fromprofileid="+profileId+ ") "  
				+" AND  (toprofileid="+freindId+" OR fromprofileid="+freindId+")") ;
				

	}
	
	public List<Profile> getRequestedFreinds( Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(
				"select P.id, aboutMe,gender,photo,place,userId from profile P, friends F ");
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
				return profile;
			}

		});
		return actors;
	}
	
	
	public List<Profile> getFreindsActivity(String userId, Integer profileId) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(
				"select u.name ,D.message,D.created,P.id ,photo,place,P.userId from profile P, friends F ,User u,Updates D ");
		stringBuilder.append("WHERE ");
		stringBuilder.append("D.profile_id_fk = P.id ");
		stringBuilder.append("AND ");
		stringBuilder.append("P.userId=U.USER_ID ");
		stringBuilder.append("AND ");
		stringBuilder.append("CASE ");
		stringBuilder.append("WHEN F.toprofileid =" + profileId + " ");
		stringBuilder.append("THEN F.fromprofileid = P.id ");
		stringBuilder.append("WHEN F.fromprofileid=" + profileId + " ");
		stringBuilder.append("THEN F.toprofileid= P.id ");
		stringBuilder.append("END ");
		stringBuilder.append("AND ");
		stringBuilder.append("F.relationstatus > '0' ");
		stringBuilder.append("ORDER BY D.update_id DESC");
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
	
	
	public void insertActivity(String message,Integer profileId) {
		

		this.jdbcTemplate.update("insert into updates (message,profile_id_fk) values (?,?)",message,profileId);

	}

	
	
	

}
