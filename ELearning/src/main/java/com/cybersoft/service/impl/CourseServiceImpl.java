package com.cybersoft.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.cybersoft.common.IndentifyUser;
import com.cybersoft.dto.*;
import com.cybersoft.entity.CourseContentEntity;
import com.cybersoft.entity.CourseEntity;
import com.cybersoft.entity.User;
import com.cybersoft.entity.Video;
import com.cybersoft.model.courses.CourseSearchModel;
import com.cybersoft.repository.CourseContentRepository;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.repository.VideoRepository;
import com.cybersoft.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cybersoft.repository.CourseRepository;
import com.cybersoft.service.CourseService;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private CourseContentRepository courseContentRepository;

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

	@Override
	public CourseSearchModel getCourseByUser(SearchCourseDto dto) {

		Optional<User> userOptional = userRepository.findById(dto.getUserId());

		CourseSearchModel result = new CourseSearchModel();

		if(!userOptional.isPresent()) {
			return result;
		}
		List<CourseDto> dtos = new ArrayList<>();

		Pageable pageable = PageRequest.of(dto.getPageIndex() - 1, dto.getPageSize());

		Set<User> users = new HashSet<>();

		users.add(userOptional.get());

		Page<CourseDto> courseDtoPage = courseRepository.findByUsersAndCourse(users, pageable);

//		Page<CourseEntity> coursePage = courseRepository.findByUsers(users, pageable);
//		List<User> userInCourse = new ArrayList<>(users);
//
//		coursePage.forEach(entity -> {
//			CourseDto dataTransfer = new CourseDto();
//			modelMapper.map(entity, dataTransfer);
//			dataTransfer.setUserEmail(userInCourse.get(0).getEmail());
//			dataTransfer.setUserFullName(userInCourse.get(0).getFullname());
//
//			dtos.add(dataTransfer);
//		});

		result.setTotal(courseDtoPage.getTotalElements());
		result.setContent(dtos);

		return result;
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

		saveCourseContent(dto.getCourseContentDtoList(), courseEntitySaved);

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

	@Transactional
	private void saveCourseContent(List<CourseContentDto> courseContentDtoList, CourseEntity courseEntity) throws RuntimeException {
		for(CourseContentDto dto : courseContentDtoList) {
			CourseContentEntity entity = new CourseContentEntity();
			entity.setContent(dto.getContent());
			entity.setCourse(courseEntity);

			CourseContentEntity savedEntity = courseContentRepository.save(entity);

			if(dto.getVideoDtos().size() > 0) {
				saveVideoOfContent(dto.getVideoDtos(), savedEntity);
			}
		}
	}

	private void saveVideoOfContent(List<VideoDto> videoDtoList, CourseContentEntity courseContentEntity) {
		for(VideoDto dto : videoDtoList) {
			try {
				Video entityVideo = new Video();
				entityVideo.setCourseContent(courseContentEntity);
				entityVideo.setUrl(dto.getUrl());
				entityVideo.setTitle(dto.getTitle());
				entityVideo.setTimeCount(dto.getTimeCount());

				videoRepository.save(entityVideo);
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

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
	public List<CourseDto> getCourseByUserEmail(String email) {
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



