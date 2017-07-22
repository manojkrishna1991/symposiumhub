package com.symposiumhub.database.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.symposiumhub.model.Coordinator;
@Service
public class CordinatorDao implements GenericDao<Coordinator, Integer>  {

	    @Autowired
	    private HibernateTemplate hibernateTemplate;

		@Override
		public Coordinator load(Integer id) {
			// TODO Auto-generated method stub
			return hibernateTemplate.load(Coordinator.class,id);
		}

		@Override
		public Coordinator get(Integer id) {
			// TODO Auto-generated method stub
			return hibernateTemplate.get(Coordinator.class,id);
		}

		@Override
		public List<Coordinator> getAll() {
			// TODO Auto-generated method stub
			return hibernateTemplate.loadAll(Coordinator.class);
		}

		@Override
		public Serializable save(Coordinator object) {
			// TODO Auto-generated method stub
			return hibernateTemplate.save(object);
		}

		@Override
		public void saveOrUpdate(Coordinator object) {
			// TODO Auto-generated method stub
			hibernateTemplate.saveOrUpdate(object);
		}

		@Override
		public void delete(Coordinator object) {
			// TODO Auto-generated method stub
			hibernateTemplate.delete(object);
		}

		@Override
		public Long count() {
			// TODO Auto-generated method stub
			 return new Long(hibernateTemplate.loadAll(Coordinator.class).size());
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			hibernateTemplate.flush();
		}
	    
	
	
	
	

}
