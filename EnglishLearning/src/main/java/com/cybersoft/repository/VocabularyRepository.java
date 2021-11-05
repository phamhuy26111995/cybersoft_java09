package com.cybersoft.repository;

import java.util.List;
import com.cybersoft.entity.Vocabulary;

public interface VocabularyRepository {
	List<Vocabulary> listAllVocabularyByUserAndCate(int userId,int cateId);
	Vocabulary findById(int id);
	void save(Vocabulary entity);
	void edit(Vocabulary entity);
	void delete(int id,int cateId);
	List<Vocabulary> listAllVocabulary(int userId);
}
