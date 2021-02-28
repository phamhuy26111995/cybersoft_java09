package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.UserDto;
import com.cybersoft.entity.User;
import com.cybersoft.entity.UserCourse;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
//	private RoleRepository roleRepository; 
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
//		this.roleRepository = roleRepository;
	}

	@Override
	public List<UserDto> getAll() {
//		List<UserDto> dtos = new ArrayList<UserDto>();
//		// GỌI PHƯƠNG THỨC TRUY VẤN LẤY DANH SÁCH USER
//		List<User> entities = userRepository.findAll();
//		// MAPPING USER ENTITY SANG USER DTO
//		for (User entity : entities) {
//			// GỌI PHƯƠNG THỨC TRUY VẤN LẤY ROLE THEO id 
//			// (roleId là khóa ngoại lưu trong bảng user)
//			Role role = roleRepository.findById(entity.getRoleId()).get();
//			UserDto dto = new UserDto(
//					entity.getId(),
//					entity.getEmail(),
//					entity.getPassword(),
//					entity.getFullname(),
//					entity.getAvatar(),
//					entity.getRoleId()
//				);
//			dto.setRoleDesc(role.getDescription());
//			dtos.add(dto);
//		}
		
		List<UserDto> dtos = userRepository.findAllJoin();
		return dtos;
	}

	@Override
	public void update(UserDto dto) {
		// TRUY VẤN LẤY RA DỮ LIỆU ĐANG LƯU TRONG DB
		User entity = userRepository.findById(dto.getId()).get();
		// MAPPING USER DTO SANG USER ENTITY
			
		if(dto.getFullname() != null && !dto.getFullname().equalsIgnoreCase("")) {
			entity.setFullname(dto.getFullname());
		}
		if(dto.getEmail() != null && !dto.getEmail().isEmpty()) {
			entity.setEmail(dto.getEmail());
		}
		if(dto.getAvatar()!=null && !dto.getAvatar().isEmpty()) {
			entity.setAvatar(dto.getAvatar());
		}
		
		if(dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			entity.setPassword(hashed);
		}
		
		
		
		
		
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		entity.setRoleId(dto.getRoleId());
		
		
	
		
		
		// NẾU NGƯỜI DÙNG NHẬP MẬT KHẨU MỚI THÌ ĐỔI LẠI MẬT KHẨU
		
		userRepository.save(entity);

		
	}
	
	

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDto getById(int id) {
		User entity = userRepository.findById(id).get();
		
		System.out.println(entity.getFullname());
		UserDto dto = new UserDto(
				entity.getId(),
				entity.getFullname(),
				entity.getEmail(),
				entity.getPassword(),
				
				entity.getAvatar(),
				entity.getRoleId()
				
		
			);
	
		return dto;
	}

	@Override
	public void insert(UserDto dto) {
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		// MAPPING USER DTO SANG USER ENTITY
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(hashed);
		entity.setFullname(dto.getFullname());
		entity.setAvatar(dto.getAvatar());
		entity.setRoleId(dto.getRoleId());
		entity.setUserCourses(dto.getUserCourses());
		userRepository.save(entity);
		
	}

	@Override
	public List<UserDto> getStudentOfCourse(int id) {
		List<UserDto> dtos = userRepository.findAllUserOfCourse(id);
		
		return dtos;
	}

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

	@Override
	public UserDto getByEmail(String email) {
		User entity = userRepository.findByEmail(email);
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
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
		entity.setRoleId(3);
		
		
		userRepository.save(entity);
		
	}

	@Override
	public void updateProfile(UserDto dto) {
		User entity = userRepository.findById(dto.getId()).get();
		if(dto.getEmail() != null && !dto.getEmail().isEmpty()) {
			entity.setEmail(dto.getEmail());
		}
		if(dto.getFullname() != null && !dto.getFullname().equalsIgnoreCase("")) {
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
		if(entity.getPassword() != null && !entity.getPassword().isEmpty()) {
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			entity.setPassword(hashed);
		}
		
		userRepository.save(entity);
		
	}

}
