package com.cybersoft.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

}
