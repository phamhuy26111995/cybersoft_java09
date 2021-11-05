package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.VocabularyDto;

public interface VocabularyService {
	List<VocabularyDto> getAllVocabularyByUserAndCate(int userId , int cateId);
	List<VocabularyDto> getAllVocabulary(int userId);
	void add(VocabularyDto dto);
	VocabularyDto getById(int id);
	void edit(VocabularyDto dto);
	void delete(int id,int cateId);
	
}
