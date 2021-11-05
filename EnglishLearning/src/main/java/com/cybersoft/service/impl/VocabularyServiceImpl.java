package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.VocabularyDto;
import com.cybersoft.entity.Category;
import com.cybersoft.entity.Vocabulary;
import com.cybersoft.repository.VocabularyRepository;
import com.cybersoft.service.VocabularyService;

@Service
public class VocabularyServiceImpl implements VocabularyService{

	private VocabularyRepository vocabularyRepository;
	public VocabularyServiceImpl(VocabularyRepository vocabularyRepository) {
		this.vocabularyRepository = vocabularyRepository;
	}
	public List<VocabularyDto> getAllVocabularyByUserAndCate(int userId , int cateId) {
		List<VocabularyDto> dtos = new ArrayList<VocabularyDto>();
		
		for(Vocabulary vol : vocabularyRepository.listAllVocabularyByUserAndCate(userId, cateId)) {
			VocabularyDto dto = new VocabularyDto();
			dto.setId(vol.getId());
			dto.setVietnamese(vol.getVietnamese());
			dto.setEnglish(vol.getEnglish());
			dto.setType(vol.getType());
			dto.setUser_id(vol.getUser_id());
			dto.setCate_id(vol.getCate_id());
			
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	public VocabularyDto getById(int id) {
		Vocabulary entity = vocabularyRepository.findById(id);
		VocabularyDto dto = new VocabularyDto(entity.getId(), entity.getVietnamese(),entity.getEnglish(),entity.getType(),entity.getUser_id(),entity.getCate_id());
		
		return dto;
	}
	
	public void add(VocabularyDto dto) {
		Vocabulary entity = new Vocabulary();
		entity.setVietnamese(dto.getVietnamese());
		entity.setEnglish(dto.getEnglish());
		entity.setType(dto.getType());
		entity.setUser_id(dto.getUser_id());
		entity.setCate_id(dto.getCate_id());
		vocabularyRepository.save(entity);
	}
	
	public void edit(VocabularyDto dto) {
		Vocabulary entity = vocabularyRepository.findById(dto.getId());
		if(entity != null) {
			entity.setVietnamese(dto.getVietnamese());
			entity.setEnglish(dto.getEnglish());
			entity.setType(dto.getType());
			entity.setUser_id(dto.getUser_id());
			entity.setCate_id(dto.getCate_id());
			vocabularyRepository.edit(entity);
		}
		
	}
	public void delete(int id, int cateId) {
		vocabularyRepository.delete(id, cateId);
		
	}
	public List<VocabularyDto> getAllVocabulary(int userId) {
		List<VocabularyDto> dtos = new ArrayList<VocabularyDto>();
		
		for(Vocabulary vol : vocabularyRepository.listAllVocabulary(userId)) {
			
			VocabularyDto dto = new VocabularyDto();
			dto.setId(vol.getId());
			dto.setVietnamese(vol.getVietnamese());
			dto.setEnglish(vol.getEnglish());
			dto.setType(vol.getType());
			dto.setUser_id(vol.getUser_id());
			dto.setCate_id(vol.getCate_id());
			
			dtos.add(dto);
		}
		
		return dtos;
	}

}
