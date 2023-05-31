package com.cybersoft.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.cybersoft.common.AppUtils;
import com.cybersoft.common.BaseDTO;
import com.cybersoft.common.IndentifyUser;
import com.cybersoft.dto.*;
import com.cybersoft.entity.*;
import com.cybersoft.model.courses.CourseSearchModel;
import com.cybersoft.repository.*;
import com.cybersoft.service.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cybersoft.service.CourseService;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CategoryRepository categoryRepository;
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
	public CourseSearchModel getAll(BaseDTO baseDTO) {
		if(baseDTO.getPageIndex() < 0) {
			throw new RuntimeException("PAGE_INDEX_MUST_NOT_LARGE_THAN_ZERO");
		}
		CourseSearchModel result = new CourseSearchModel();

		Pageable pageable = PageRequest.of(baseDTO.getPageIndex() - 1, baseDTO.getPageSize());

		Page<CourseDto> pageResult = courseRepository.findAllPaging(pageable);

		result.setContent(pageResult.getContent());
		result.setTotal(pageResult.getTotalElements());

		return result;

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

	@Override
	public CourseSearchModel search(SearchCourseDto dto) {
		CourseSearchModel result = new CourseSearchModel();
		List<CourseDto> dtos = courseRepository.findCourseByCondition(dto);
		result.setContent(dtos);
		AppUtils.createOrderNumber(result.getContent(), dto.getPageIndex(), dto.getPageSize());

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
	public CourseEntity save(CourseDto dto, MultipartFile file) {

		CourseEntity entity = new CourseEntity();

		CourseEntity courseEntitySaved = courseRepository.save(setDataEntity(entity, dto, file));

//		saveCourseContent(dto.getCourseContentDtoList(), courseEntitySaved);

		Set<CourseEntity> courseEntitySet = null;
		Optional<User> user = userRepository.findById(IndentifyUser.getIdPrincipal());
		if(!user.isPresent()) {
			return null;
		}

		courseEntitySet = user.get().getUserCourse();
		courseEntitySet.add(courseEntitySaved);

		user.get().setUserCourse(courseEntitySet);

		userRepository.save(user.get());

		return courseEntitySaved;
	}

	private CourseEntity setDataEntity(CourseEntity entity, CourseDto dto, MultipartFile file) {

		entity.setLecturesCount(dto.getLecturesCount());
		entity.setHourCount(dto.getHourCount());
		entity.setDiscount(dto.getDiscount());
		entity.setPrice(dto.getPrice());
		entity.setPromotionPrice(dto.getPromotionPrice());
		entity.setDescription(dto.getDescription());
		entity.setContent(dto.getContent());
		entity.setDescription(dto.getDescription());
		entity.setTitle(dto.getTitle());

		if(dto.getCategoryId() != null) {
			Category categoryEntity = categoryRepository.getOne(dto.getCategoryId());
			entity.setCategory(Optional.of(categoryEntity).orElse(null));
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
	public CourseEntity update(CourseDto dto ,MultipartFile file) {
		Optional<CourseEntity> entityOptional = courseRepository.findById(dto.getId());
		return entityOptional.map(courseEntity -> courseRepository.save(setDataEntity(courseEntity, dto, file))).orElse(null);
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
		List<CourseContentDto> courseContentDtoList = new ArrayList<>();
		List<VideoDto> videoDtos = new ArrayList<>();
		if(!courseEntityOptional.isPresent()) {
			return null;
		}

		CourseDto dto = new CourseDto();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.map(courseEntityOptional.get(), dto);

		for(CourseContentEntity courseContentEntity : courseEntityOptional.get().getCourseContents()) {
			CourseContentDto courseContentDto = new CourseContentDto();

			for(Video video : courseContentEntity.getVideos()) {
				VideoDto videoDto = new VideoDto();
				videoDto.setId(video.getId());
				videoDto.setUrl(video.getUrl());
				videoDto.setTitle(video.getTitle());
				videoDto.setTimeCount(video.getTimeCount());

				videoDtos.add(videoDto);
			}
			courseContentDto.setContent(courseContentEntity.getContent());
			courseContentDto.setVideoDtos(videoDtos);
			courseContentDto.setId(courseContentEntity.getId());

			courseContentDtoList.add(courseContentDto);
		}

		dto.setCourseContentDtoList(courseContentDtoList);

		return dto;
	}

}



