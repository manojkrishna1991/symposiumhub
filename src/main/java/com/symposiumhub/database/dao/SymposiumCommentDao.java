package com.symposiumhub.database.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.symposiumhub.model.SymposiumComment;

@Service
public class SymposiumCommentDao implements  GenericDao<SymposiumComment,String>{


	@Autowired
	 private HibernateTemplate hibernateTemplate;

	@Override
	public SymposiumComment load(String id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.load(SymposiumComment.class,id);
	}

	@Override
	public SymposiumComment get(String id) {
		// TODO Auto-generated method stub
		int commentId=Integer.valueOf(id);
		return hibernateTemplate.get(SymposiumComment.class,commentId);
	}

	@Override
	public List<SymposiumComment> getAll() {
		// TODO Auto-generated method stub
		return hibernateTemplate.loadAll(SymposiumComment.class);
	}

	@Override
	public Serializable save(SymposiumComment object) {
		// TODO Auto-generated method stub
		return hibernateTemplate.save(object);
	}

	@Override
	public void saveOrUpdate(SymposiumComment object) {
	
		hibernateTemplate.saveOrUpdate(object);
		
	}

	@Override
	public void delete(SymposiumComment object) {
		
		hibernateTemplate.delete(object);
		
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		 return new Long(hibernateTemplate.loadAll(SymposiumComment.class).size());
	}

	@Override
	public void flush() {
		
		hibernateTemplate.flush();
		
	}
}
