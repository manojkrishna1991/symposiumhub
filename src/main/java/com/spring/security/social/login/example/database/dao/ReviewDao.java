package com.spring.security.social.login.example.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.spring.security.social.login.example.database.model.Profile;
import com.spring.security.social.login.example.database.model.Review;

@Service
public class ReviewDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	public Review saveReview(Review review) {

		hibernateTemplate.merge(review);

		return review;

	}

	public List<Review> getReviewsByCollgeId(Integer id) {

		String hql = "FROM Review E WHERE E.collegeId =?";

		Object[] queryParam = { id };

		@SuppressWarnings("unchecked")
		List<Review> review = (List<Review>) hibernateTemplate.find(hql, queryParam);

		return review;

	}

	public Integer getAverageReviews(Integer id) {

		String sql = "select AVG(rating) from review where collegeId=?";

		Integer a = (Integer) this.jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);

		return a;
	}

}
