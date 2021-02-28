
package com.cybersoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.User;
import com.cybersoft.dto.UserDto;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT new com.cybersoft.dto.UserDto(u.id, u.fullname, u.email, r.description) FROM User u JOIN Role r ON u.roleId = r.id")
	public List<UserDto> findAllJoin();
	
	public User findByEmail(String email);
	
	@Query("SELECT new com.cybersoft.dto.UserDto(u.id, u.fullname, u.email,r.description) FROM UserCourse uc JOIN Course c ON uc.course.id = c.id JOIN User u ON uc.user.id = u.id JOIN Role r ON uc.roleId = r.id WHERE u.roleId = 3 AND c.id=?1")
	public List<UserDto> findAllUserOfCourse(int id);
	
	public User findTop1ByOrderByIdDesc();
}
