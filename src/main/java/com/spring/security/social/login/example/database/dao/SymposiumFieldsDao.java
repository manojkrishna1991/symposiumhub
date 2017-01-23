package com.spring.security.social.login.example.database.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.spring.security.social.login.example.database.model.Symposium;
import com.spring.security.social.login.example.database.model.SymposiumRegistrationFields;
@Service
public class SymposiumFieldsDao  implements GenericDao<SymposiumRegistrationFields, Integer>  { 
	 @Autowired
	    private HibernateTemplate hibernateTemplate;

		@Override
		public SymposiumRegistrationFields load(Integer id) {
			// TODO Auto-generated method stub
			return hibernateTemplate.load(SymposiumRegistrationFields.class,id);
		}

		@Override
		public SymposiumRegistrationFields get(Integer id) {
			// TODO Auto-generated method stub
			return hibernateTemplate.get(SymposiumRegistrationFields.class,id);
		}

		@Override
		public List<SymposiumRegistrationFields> getAll() {
			// TODO Auto-generated method stub
			return hibernateTemplate.loadAll(SymposiumRegistrationFields.class);
		}

		@Override
		public Serializable save(SymposiumRegistrationFields object) {
			// TODO Auto-generated method stub
			return hibernateTemplate.save(object);
		}

		@Override
		public void saveOrUpdate(SymposiumRegistrationFields object) {
			// TODO Auto-generated method stub
			hibernateTemplate.saveOrUpdate(object);
		}

		@Override
		public void delete(SymposiumRegistrationFields object) {
			// TODO Auto-generated method stub
			hibernateTemplate.delete(object);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			 return new Long(hibernateTemplate.loadAll(SymposiumRegistrationFields.class).size());
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			hibernateTemplate.flush();
		}
	    

	
	    
}
