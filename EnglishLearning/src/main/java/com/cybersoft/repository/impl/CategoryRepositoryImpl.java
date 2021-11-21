package com.cybersoft.repository.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.cybersoft.common.CurrentUser;
import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.UserDetailsDto;
import com.cybersoft.entity.Category;
import com.cybersoft.entity.UserCategory;
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
	
	public Integer findLastId() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT MAX(id) FROM Category";
		Query<Integer> query = session.createQuery(hql);
		Integer lastID = query.list().get(0);
		
		return lastID;
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


	public List<Category> listAllCategoryByUser() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserCategory uc INNER JOIN Category c ON uc.cate_id = c.id "
				+ "INNER JOIN User u ON uc.user_id = u.id WHERE u.username = :username";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = CurrentUser.getPrincipal().getUsername();
		
		Category category = null;
		Query<Object> query = session.createQuery(hql);
		query.setParameter("username", username);
		List<Object> result = (List<Object>) query.list(); 
		List<Category> entities = new ArrayList<Category>();
		Iterator itr = result.iterator();
		while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next();
		   //now you have one array of Object for each row
		   category = (Category)obj[1];
		   entities.add(category);
		   //same way for all obj[2], obj[3], obj[4]
		}
		
		return entities;
	}

}
