package com.cybersoft.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.UserCategory;
import com.cybersoft.repository.UserCategoryRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class UserCategoryRepositoryImpl implements UserCategoryRepository {
	private SessionFactory sessionFactory;
	
	public UserCategoryRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	

	public void save(UserCategory userCategory) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(userCategory);
		
	}
}
