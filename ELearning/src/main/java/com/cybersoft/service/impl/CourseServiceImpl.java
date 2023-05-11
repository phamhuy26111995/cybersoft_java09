package com.cybersoft.service.impl;

import java.util.*;

import com.cybersoft.common.IndentifyUser;
import com.cybersoft.entity.CourseEntity;
import com.cybersoft.entity.User;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.repository.CourseRepository;
import com.cybersoft.service.CourseService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CourseDto> getAll() {
		List<CourseDto> dtos = new ArrayList<CourseDto>();
		try {
			List<CourseEntity> entities = courseRepository.findAll();
			for(CourseEntity entity : entities) {
				CourseDto dto = new CourseDto(entity.getId(), 
						entity.getTitle(), 
						entity.getImage(), 
						entity.getLecturesCount(),
						entity.getPrice());
				dto.setHourCount(entity.getHourCount());
				dto.setContent(entity.getContent());
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	//Lấy course theo id
	@Override
	public CourseDto getById(Long id) {
		CourseEntity entity = courseRepository.findById(id).get();

		CourseDto dto = new CourseDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setLecturesCount(entity.getLecturesCount());
		dto.setHourCount(entity.getHourCount());
		dto.setViewCount(entity.getViewCount());
		dto.setDiscount(entity.getDiscount());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		dto.setPromotionPrice(entity.getPromotionPrice());
		dto.setContent(entity.getContent());

		return dto;

	}
	
	//Thêm mới một course
	@Override
	public void save(CourseDto dto, MultipartFile file) {

		CourseEntity entity = new CourseEntity();

		CourseEntity courseEntitySaved = courseRepository.save(setDataEntity(entity, dto, file));

		Set<CourseEntity> courseEntitySet = null;
		Optional<User> user = userRepository.findById(IndentifyUser.getIdPrincipal());
		if(!user.isPresent()) {
			return;
		}

		courseEntitySet = user.get().getUserCourse();
		courseEntitySet.add(courseEntitySaved);

		user.get().setUserCourse(courseEntitySet);

		userRepository.save(user.get());


	}

	private CourseEntity setDataEntity(CourseEntity entity, CourseDto dto, MultipartFile file) {

		entity.setLecturesCount(dto.getLecturesCount());
		entity.setHourCount(dto.getHourCount());
		entity.setDiscount(dto.getDiscount());
		entity.setPrice(dto.getPrice());
		entity.setDescription(dto.getDescription());
		entity.setContent(dto.getContent());
		entity.setDescription(dto.getDescription());
		entity.setTitle(dto.getTitle());

		if(dto.getCategory() != null) {
			entity.setCategory(dto.getCategory());
		}

		if(file != null) {
			entity.setImage(cloudinaryService.uploadFile(file));
		}

		return entity;
	}




	//Edit một course
	@Override
	public void update(CourseDto dto ,MultipartFile file) {
		Optional<CourseEntity> entityOptional = courseRepository.findById(dto.getId());
		if(!entityOptional.isPresent()) {
			return;
		}

		courseRepository.save(setDataEntity(entityOptional.get(), dto, file));

	}
	
	//Xóa một course
	@Override
	public void delete(Long id) {

		Optional<CourseEntity> optionalCourse = courseRepository.findById(id);

		if(!optionalCourse.isPresent()) {
			return;
		}

		CourseEntity entity = optionalCourse.get();

		for(User user : entity.getUsers()) {
			user.getUserCourse().remove(entity);
			userRepository.save(user);
		}

		courseRepository.deleteById(id);

	}
	
	//Lấy toàn bộ khóa học thuộc về user theo email của user
	@Override
	public List<CourseDto> getCourseByUser(String email) {
//		List<CourseDto> dtos = courseRepository.getCourseByUser(email);
		return null;

	}
	
	//Lấy toàn bộ course thuộc category
	@Override
	public List<CourseDto> getCourseByCategory(int id) {
		List<CourseDto> dtos = courseRepository.getCourseByCategory(id);
		return dtos;
	}
	
	//Lấy ra course đang nằm cuối danh sách
	@Override
	public CourseDto getTheLastCourse() {
		CourseEntity entity = courseRepository.findTop1ByOrderByIdDesc();
		CourseDto dto = new CourseDto();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setLecturesCount(entity.getLecturesCount());
		dto.setHourCount(entity.getHourCount());
		dto.setViewCount(entity.getViewCount());
		dto.setDiscount(entity.getDiscount());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		dto.setPromotionPrice(entity.getPromotionPrice());

		return dto;
	}

	@Override
	public CourseDto findById(Long id) {

		Optional<CourseEntity> courseEntityOptional = courseRepository.findById(id);
		if(!courseEntityOptional.isPresent()) {
			return null;
		}

		CourseDto dto = new CourseDto();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.map(courseEntityOptional.get(), dto);

		return dto;
	}

}



