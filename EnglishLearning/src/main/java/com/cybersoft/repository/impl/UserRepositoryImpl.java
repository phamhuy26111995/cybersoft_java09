package com.cybersoft.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.User;
import com.cybersoft.repository.UserRepository;
@Repository
@Transactional(rollbackOn = Exception.class)
public class UserRepositoryImpl implements UserRepository {
	
	private SessionFactory sessionFactory;
	public UserRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public User findById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String id = String.valueOf(userId);
		return session.find(User.class, id);
	}
	
	public User findByUserName(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE username = :username";
		Query<User> query = session.createQuery(hql,User.class);
		query.setParameter("username", username);
		
		List<User> result = query.getResultList();
		if(result.size() > 0) {
			return result.get(0);
		}
		
		return null;
	}

	public void registerUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

}
