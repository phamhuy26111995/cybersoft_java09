package com.cybersoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.dto.VideoDto;
import com.cybersoft.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
	
	@Query("SELECT new com.cybersoft.dto.VideoDto(v.id, v.title, v.url,v.image,v.timeCount, c.description) FROM Video v JOIN Course c ON v.courseId = c.id")
	public List<VideoDto> findAllJoin(); 
	
	@Query("SELECT new com.cybersoft.dto.VideoDto(v.id, v.title, v.url,v.image,v.timeCount, c.description) FROM Video v JOIN Course c ON v.courseId = c.id WHERE c.id = ?1")
	public List<VideoDto> findVideosByUser(int id);
}
