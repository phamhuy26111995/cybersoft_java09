package com.cybersoft.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.entity.Category;
import com.cybersoft.repository.CategoryRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class CategoryRepositoryImpl implements CategoryRepository {
	
	private SessionFactory sessionFactory;
	
	public CategoryRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	public Category findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Category.class, id);
	}

	
	public List<Category> listAllCategory() {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("FROM Category", Category.class);
		return query.getResultList();
	}
	
	public void save(Category entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}
	
	public void edit(Category entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}
	
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(findById(id));
	}

}
