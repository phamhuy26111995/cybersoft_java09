package com.cybersoft.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cybersoft.common.AppUtils;
import com.cybersoft.common.BaseService;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.UserFilterDto;
import com.cybersoft.entity.CourseEntity;
import com.cybersoft.entity.Role;
import com.cybersoft.model.users.UsersModel;
import com.cybersoft.repository.RoleRepository;
import com.cybersoft.service.CloudinaryService;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.UserDto;
import com.cybersoft.entity.User;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private RoleRepository roleRepository;


	//Lấy toàn bộ thông tin User để hiển thị lên Front End
	@Override
	public List<UserDto> getAll() {
		
		List<UserDto> dtos = userRepository.findAllUser();
		return dtos;
	}

	//Edit User
	@Override
	public void update(UserDto dto, MultipartFile file) {
		User entity = userRepository.findById(dto.getId()).orElse(null);

		if(entity == null) {
			return;
		}

		if(StringUtils.isNotEmpty(dto.getFullname())) {
			entity.setFullname(dto.getFullname());
		}
		if(StringUtils.isNotEmpty(dto.getEmail())) {
			entity.setEmail(dto.getEmail());
		}
		if(!file.isEmpty()) {
			entity.setAvatar(cloudinaryService.uploadFile(file));
		}

		if(StringUtils.isNotEmpty(dto.getPassword())) {
			entity.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
		}

		if(StringUtils.isNotEmpty(dto.getAddress())) {
			entity.setAddress(dto.getAddress());
		}

		if(StringUtils.isNotEmpty(dto.getPhone())) {
			entity.setPhone(dto.getPhone());
		}

		if(dto.getRoleId() > 0) {
			entity.setRoleId(dto.getRoleId());
		}

		userRepository.save(entity);

	}

	
	
	//Xóa user
	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	
	//Get User theo id
	@Override
	public UserDto getById(Long id) {
		User entity = userRepository.findById(id).orElse(null);
		if(entity == null) {
			return null;
		}

		Set<CourseDto> courseList = new HashSet<>();

		for(CourseEntity courseEntity : entity.getUserCourse()) {
			CourseDto courseDto = new CourseDto();


			modelMapper.getConfiguration().setSkipNullEnabled(true);
			modelMapper.map(courseEntity, courseDto);

			courseList.add(courseDto);
		}

		AppUtils.createOrderNumberWithoutPaging(courseList);

		return new UserDto(
				entity.getId(),
				entity.getFullname(),
				entity.getEmail(),
				entity.getAvatar(),
				entity.getRoleId(),
				courseList
		);
	}

	//Thêm mới một user
	@Override
	public void save(UserDto dto, MultipartFile file) {
		Role role = roleRepository.getOne(dto.getRoleId());
		if(role == null) {
			return;
		}
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		// MAPPING USER DTO SANG USER ENTITY
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(hashed);
		entity.setFullname(dto.getFullname());
		entity.setRoleId(role.getId());

		if(!file.isEmpty()) {
			entity.setAvatar(cloudinaryService.uploadFile(file));
		}
		userRepository.save(entity);
	}

	//Lấy các user là Student thuộc về khóa học
	@Override
	public List<UserDto> getStudentOfCourse(Long id) {
//		List<UserDto> dtos = userRepository.findAllUserOfCourse(id);
		
		return null;
	}

	//Lấy user cuối cùng trong danh sách
	@Override
	public UserDto getTheLastUser() {
		User entity= userRepository.findTop1ByOrderByIdDesc();
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFullname(entity.getFullname());
		dto.setEmail(entity.getEmail());
		dto.setRoleId(entity.getRoleId());
		return dto;
		
	}

	//Tìm kiếm user theo email
	@Override
	public UserDto getByEmail(String email) {
		User entity = userRepository.findByEmail(email);
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setAvatar(entity.getAvatar());
		dto.setEmail(entity.getEmail());
		dto.setFullname(entity.getFullname());
		return dto;
	}

	@Override
	public void insertUserRegister(UserDto dto) {
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		// MAPPING USER DTO SANG USER ENTITY
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(hashed);
		entity.setFullname(dto.getFullname());
		entity.setRoleId(dto.getRoleId());


		userRepository.save(entity);

	}

	@Override
	public void updateProfile(UserDto dto) {
		User entity = userRepository.findById(dto.getId()).get();
		if(dto.getEmail() != null && !dto.getEmail().isEmpty()) {
			entity.setEmail(dto.getEmail());
		}
		if(dto.getFullname() != null && !dto.getFullname().isEmpty()) {
			entity.setFullname(dto.getFullname());
		}
		if(dto.getAddress() != null && !dto.getAddress().isEmpty()) {
			entity.setAddress(dto.getAddress());
		}
		if(dto.getPhone() != null && !dto.getPhone().isEmpty()) {
			entity.setPhone(dto.getPhone());
		}
		if(dto.getAvatar() != null && !dto.getAvatar().isEmpty()) {
			entity.setAvatar(dto.getAvatar());
		}
		if(dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			entity.setPassword(hashed);
		}
		
		userRepository.save(entity);
		
	}

	@Override
	public UsersModel getUsersByRole(UserFilterDto dto) {
		UsersModel model = new UsersModel();
		Page<UserDto> usersPaging;
		Pageable pageable = PageRequest.of(dto.getPageIndex() - 1 , dto.getPageSize());
		if(dto.getRoleName() == null) {
			usersPaging = userRepository.findAllWithPaging(pageable);
		} else {
			usersPaging = userRepository.findAllByRole(dto.getRoleName(), pageable);
		}
		List<UserDto> users = usersPaging.getContent();
		model.setTotal(usersPaging.getTotalElements());
		model.setUsers(users);

 		return model;
	}

}
