package com.symposiumhub.database.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.symposiumhub.model.Symposium;
import com.symposiumhub.model.User;
/**
 * 	
 * @author manoj
 *
 */

@Service
public class SymposiumDao implements GenericDao<Symposium,String> {
	 
	
	@Autowired
	 private HibernateTemplate template;



	@Override
	public Symposium get(String id) {
		// TODO Auto-generated method stub
		return template.get(Symposium.class,id);
	}

	@Override
	public List<Symposium> getAll() {
		// TODO Auto-generated method stub
		 return template.loadAll(Symposium.class);
	}

	@Override
	public Serializable save(Symposium object) {
		// TODO Auto-generated method stub
		 return template.save(object);
	}

	@Override
	public void saveOrUpdate(Symposium object) {
		// TODO Auto-generated method stub
		template.saveOrUpdate(object);
	}

	@Override
	public void delete(Symposium object) {
		// TODO Auto-generated method stub
		template.delete(object);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return new Long(template.loadAll(Symposium.class).size());
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		 template.flush();
	}

	@Override
	public Symposium load(String id) {
		// TODO Auto-generated method stub
		return template.load(Symposium.class,id);
	}

	
	public List<Symposium> getSymposiums(String userId)
	{
		 Query q = template.getSessionFactory().getCurrentSession().createQuery("FROM Symposium E WHERE E.userId =:userId  order by createdDate desc");
		 q.setFirstResult(0); // modify this to adjust paging
		 q.setMaxResults(1);
		 q.setParameter("userId", userId);
		 List<Symposium> symposium = (List<Symposium>) q.list();
		 return symposium;
		
	}
	
	public List<Symposium> getSymposiumsBySymposiumId(String userId,String symposiumId)
	{
		String hql = "FROM Symposium E WHERE E.userId =? and  E.symposiumid=?" ;
		
		Object[] queryParam = {userId,symposiumId};
		
		@SuppressWarnings("unchecked")
		List<Symposium> symposium = (List<Symposium>) template.find(hql, queryParam);
		
		return symposium;
		
	}
	public List<Symposium> getSymposiumName(String userId)
	{
		String hql = " FROM Symposium E WHERE E.userId =? order by createdDate desc" ;
		
		Object[] queryParam = {userId};
		
		@SuppressWarnings("unchecked")
		List<Symposium> symposium = (List<Symposium>) template.find(hql, queryParam);
		
		return symposium;
		
	}
	public List<Symposium> getAllSymposium()
	{
		String hql = " FROM Symposium order by createdDate desc " ;
		
		
		@SuppressWarnings("unchecked")
		List<Symposium> symposium = (List<Symposium>) template.find(hql);
		
		return symposium;
		
	}
	
	public List<Symposium> getAllSymposiumByLimit()
	{
		 Query q = template.getSessionFactory().getCurrentSession().createQuery(" FROM Symposium order by createdDate desc");
		 q.setFirstResult(0); // modify this to adjust paging
		 q.setMaxResults(4);
		 List<Symposium> symposium = (List<Symposium>) q.list();
		 
		 return symposium;
		
	}
	
	public List<Object[]> getValidSymposiumRegistrations() {
		List<Object[]> symposiumRegistrationsList = new ArrayList<Object[]>();
		Query q = template.getSessionFactory().getCurrentSession().createSQLQuery(
				"select  count(r.id),s.name,s.regEmail,r.symposiumid  from  symposium s INNER JOIN regsiterforsymposium r "
						+ "ON  s.symposiumid=r.symposiumid and r.isMailSent=false group by r.symposiumid");
		
		symposiumRegistrationsList = (List<Object[]>) q.list();
		
		
		return symposiumRegistrationsList;

	}
	
	
	public void updateStatusToIsMailSent(List<String> list) {
		
		try{
		
		Query q = template.getSessionFactory().getCurrentSession().createSQLQuery(
				"UPDATE regsiterforsymposium set isMailSent=true where symposiumid in (:list) ");
		
		q.setParameterList("list", list);
		
		q.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	

	}
	
	
	
	
	
}
