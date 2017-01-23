package com.spring.security.social.login.example.database.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.spring.security.social.login.example.database.model.RegisterForASymposium;
import com.spring.security.social.login.example.database.model.Subscribe;

@Service
public class SubscribeDao implements GenericDao<Subscribe, Integer>  {

    @Autowired
    private HibernateTemplate hibernateTemplate;

	@Override
	public Subscribe load(Integer id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.load(Subscribe.class,id);
	}

	@Override
	public Subscribe get(Integer id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(Subscribe.class,id);
	}

	@Override
	public List<Subscribe> getAll() {
		// TODO Auto-generated method stub
		return hibernateTemplate.loadAll(Subscribe.class);
	}

	@Override
	public Serializable save(Subscribe object) {
		// TODO Auto-generated method stub
		return hibernateTemplate.save(object);
	}

	@Override
	public void saveOrUpdate(Subscribe object) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(object);
	}

	@Override
	public void delete(Subscribe object) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(object);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		 return new Long(hibernateTemplate.loadAll(Subscribe.class).size());
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		hibernateTemplate.flush();
	}
    
}