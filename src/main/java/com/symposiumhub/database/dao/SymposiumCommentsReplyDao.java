package com.spring.security.social.login.example.database.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.spring.security.social.login.example.database.model.SymposiumCommentsReply;

@Service
public class SymposiumCommentsReplyDao implements GenericDao<SymposiumCommentsReply, String> {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public SymposiumCommentsReply load(String id) {

		return hibernateTemplate.load(SymposiumCommentsReply.class, id);
	}

	@Override
	public SymposiumCommentsReply get(String id) {

		return hibernateTemplate.get(SymposiumCommentsReply.class, id);
	}

	@Override
	public List<SymposiumCommentsReply> getAll() {

		return hibernateTemplate.loadAll(SymposiumCommentsReply.class);
	}

	@Override
	public Serializable save(SymposiumCommentsReply object) {

		return hibernateTemplate.save(object);
	}

	@Override
	public void saveOrUpdate(SymposiumCommentsReply object) {
		hibernateTemplate.saveOrUpdate(object);

	}

	@Override
	public void delete(SymposiumCommentsReply object) {
		hibernateTemplate.delete(object);

	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return new Long(hibernateTemplate.loadAll(SymposiumCommentsReply.class).size());
	}

	@Override
	public void flush() {

		hibernateTemplate.flush();

	}
}
