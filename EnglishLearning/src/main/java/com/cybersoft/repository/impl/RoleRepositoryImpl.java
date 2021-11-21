package com.cybersoft.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.Role;
import com.cybersoft.repository.RoleRepository;
@Repository
@Transactional(rollbackOn = Exception.class)
public class RoleRepositoryImpl implements RoleRepository{
	

	private SessionFactory sessionFactory;
	public RoleRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Role findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Role.class, id);
	}

	public List<Role> listAllroles() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Role";
		Query<Role> query = session.createQuery(hql, Role.class);
		
		return query.getResultList();
	}

}
