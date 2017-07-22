package com.symposiumhub.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.symposiumhub.model.DashBoardData;

@Repository
public class DashBoardDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public Serializable save(DashBoardData object) {
		// TODO Auto-generated method stub
		return hibernateTemplate.save(object);
	}

	public List<DashBoardData> fetchDashBoardData()
	{
		List<DashBoardData> dashBoardData=new ArrayList<>();
		 Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery("");
				 List<Object[]> rows = q.list();
				 for(Object[] row : rows){
					 DashBoardData dash = new DashBoardData();
				
				 }
		 
		 return dashBoardData;
		
	}
}
