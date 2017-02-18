package com.spring.security.social.login.example.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.spring.security.social.login.example.database.model.College;
import com.spring.security.social.login.example.database.model.Profile;

@Service(value = "college")
public class CollegeComponent {

	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcInsert insertProfile;

	@Value("${imagepath}")
	private String imagePath;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertProfile = new SimpleJdbcInsert(dataSource).withTableName("college");

	}

	public List<College> getCollege() {


		List<College> college = this.jdbcTemplate.query("select id,name,rating,compressedPath,compressedPathOne from college ",
				new RowMapper<College>() {
					public College mapRow(ResultSet rs, int rowNum) throws SQLException {
						College college = new College();

						college.setId(Integer.parseInt(rs.getString("id")));
						college.setName(rs.getString("name"));
						college.setRating(rs.getInt("rating"));
						college.setCompressedPath(rs.getString("compressedPath"));
						college.setCompressedPathOne(rs.getString("compressedPathOne"));

						return college;
					}

				});
		return college;
	}

	public List<College> getCollegeLimit(Integer startLimit) {

		List<College> actors = this.jdbcTemplate.query("select id,name,rating,compressedPath,compressedPathOne from college limit "+startLimit+",100",
				new RowMapper<College>() {
					public College mapRow(ResultSet rs, int rowNum) throws SQLException {
						College college = new College();

						college.setId(Integer.parseInt(rs.getString("id")));
						college.setName(rs.getString("name"));
						college.setRating(rs.getInt("rating"));
						college.setCompressedPath(rs.getString("compressedPath"));
						college.setCompressedPathOne(rs.getString("compressedPathOne"));

						return college;
					}

				});
		return actors;
	}

	public List<College> getCollegeById(Integer id) {

		List<College> actors = this.jdbcTemplate.query(
				"select id,name,createDate,compressedPath,compressedPathOne,rating from college where id=?", new Object[] { id },
				new RowMapper<College>() {
					public College mapRow(ResultSet rs, int rowNum) throws SQLException {
						College college = new College();
						college.setId(Integer.parseInt(rs.getString("id")));
						college.setName(rs.getString("name"));
						college.setCreatedDate(rs.getDate("createDate"));
						college.setCompressedPath(rs.getString("compressedPath"));
						college.setCompressedPathOne(rs.getString("compressedPathOne"));
						college.setRating(rs.getInt("rating"));

						return college;
					}

				});
		return actors;
	}

	public void update(College college) {

		this.jdbcTemplate.update("update college set  compressedPath= ? ,compressedPathOne=? where id = ?",
				college.getCompressedPath(), college.getCompressedPathOne(), college.getId());

	}

	public void updateRating(Integer rating, Integer id) {

		this.jdbcTemplate.update("update college set  rating=?  where id = ?", rating, id);

	}
}
