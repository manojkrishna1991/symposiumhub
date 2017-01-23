package com.spring.security.social.login.example.database.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.spring.security.social.login.example.database.model.RegisterForASymposium;
import com.spring.security.social.login.example.database.model.SymposiumRegistrationFields;
@Service
public class RegsiterForSymposiumDao implements GenericDao<RegisterForASymposium, Integer>  {

    @Autowired
    private HibernateTemplate hibernateTemplate;

	@Override
	public RegisterForASymposium load(Integer id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.load(RegisterForASymposium.class,id);
	}

	@Override
	public RegisterForASymposium get(Integer id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(RegisterForASymposium.class,id);
	}

	@Override
	public List<RegisterForASymposium> getAll() {
		// TODO Auto-generated method stub
		return hibernateTemplate.loadAll(RegisterForASymposium.class);
	}

	@Override
	public Serializable save(RegisterForASymposium object) {
		// TODO Auto-generated method stub
		return hibernateTemplate.save(object);
	}

	@Override
	public void saveOrUpdate(RegisterForASymposium object) {
		// TODO Auto-generated method stub
		hibernateTemplate.saveOrUpdate(object);
	}

	@Override
	public void delete(RegisterForASymposium object) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(object);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		 return new Long(hibernateTemplate.loadAll(RegisterForASymposium.class).size());
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		hibernateTemplate.flush();
	}
    
	public List<RegisterForASymposium> getSymposiumRegistrationaBySympId(String symposiumId) {
		// TODO Auto-generated method stub
		List<RegisterForASymposium> symposium=null;
		try{
		String hql = " FROM RegisterForASymposium E WHERE E.symposium.symposiumid =?" ;
		
		Object[] queryParam = {symposiumId};
		
		
		symposium= (List<RegisterForASymposium>) hibernateTemplate.find(hql, queryParam);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return symposium;
	}
	

}
