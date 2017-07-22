package com.symposiumhub.database.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.symposiumhub.model.Papers;

@Service
public class PapersDao implements GenericDao<Papers, Integer>   {


    @Autowired
    private HibernateTemplate hibernateTemplate;

	@Override
	public Papers load(Integer id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.load(Papers.class,id);
	}

	@Override
	public Papers get(Integer id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Papers.class,id);
	}

	@Override
	public List<Papers> getAll() {
		// TODO Auto-generated method stub
		return hibernateTemplate.loadAll(Papers.class);
	}

	@Override
	public Serializable save(Papers object) {
		// TODO Auto-generated method stub
		return hibernateTemplate.save(object);
	}

	@Override
	public void saveOrUpdate(Papers object) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(object);
		
	}

	@Override
	public void delete(Papers object) {
		
		hibernateTemplate.delete(object);
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		 return new Long(hibernateTemplate.loadAll(Papers.class).size());
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		hibernateTemplate.flush();
		
	}

}
