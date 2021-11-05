package com.cybersoft.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.cybersoft.entity.Vocabulary;
import com.cybersoft.repository.VocabularyRepository;

@Repository
@Transactional(rollbackOn = Exception.class)
public class VocabularyRepositoryImpl implements VocabularyRepository{
	
	private SessionFactory sessionFactory;
	public VocabularyRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<Vocabulary> listAllVocabularyByUserAndCate(int userId, int cateId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Vocabulary WHERE user_id = :userId AND cate_id = :cateId";
		Query<Vocabulary> query = session.createQuery(hql, Vocabulary.class);
		query.setParameter("userId", userId);
		query.setParameter("cateId", cateId);
		return query.getResultList();
	
	}
	
	public Vocabulary findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.find(Vocabulary.class, id);
	}
	
	
	public void save(Vocabulary entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}
	
	public void edit(Vocabulary entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}
	
	public void delete(int id, int cateId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE FROM Vocabulary WHERE cate_id = :cateId AND id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("cateId", cateId);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	public List<Vocabulary> listAllVocabulary(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Vocabulary WHERE user_id = : userId";
		Query<Vocabulary> query = session.createQuery(hql,Vocabulary.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}
