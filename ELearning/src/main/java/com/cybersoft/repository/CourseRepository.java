package com.cybersoft.repository;

import java.util.List;
import java.util.Set;

import com.cybersoft.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.CourseEntity;
import com.cybersoft.dto.CourseDto;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long>, CourseRepositoryCustom {
    //Trả về một đối tượng CourseDto thuộc user đang tương tác với hệ thống
//	@Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount,c.price,c.hourCount,c.description,c.discount,c.promotionPrice,c.viewCount) FROM UserCourse uc JOIN Course c ON uc.course.id = c.id JOIN User u ON uc.user.id = u.id WHERE u.email = ?1")
//	public List<CourseDto> getCourseByUser(String email);


    //Trả về một đối tượng CourseDto thuộc Category
    @Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount,c.price,c.hourCount,c.description,c.discount,c.promotionPrice,c.viewCount,c.content) FROM CourseEntity c JOIN Category ca ON c.category.id = ca.id WHERE ca.id = ?1 ")
    public List<CourseDto> getCourseByCategory(int id);

    // Mapping Trả về entity
    @Query("SELECT c FROM CourseEntity c JOIN c.users u WHERE u IN :users")
    Page<CourseEntity> findByUsers(@Param("users") Set<User> users, Pageable pageable);

    // Mapping Trả về một Dto
    @Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount,c.price,c.hourCount,c.description,c.discount,c.promotionPrice,c.viewCount,c.content,u.email, u.fullname)" + "  FROM CourseEntity c JOIN c.users u WHERE u IN :users")
    Page<CourseDto> findByUsersAndCourse(@Param("users") Set<User> users, Pageable pageable);


    @Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.lecturesCount,c.price,c.hourCount,c.description,c.discount,c.promotionPrice,c.viewCount,c.content,u.email, u.fullname, category.title)" + "  FROM CourseEntity c JOIN c.users u JOIN c.category category")
    Page<CourseDto> findAllPaging(Pageable pageable);

    //Tìm Course cuối cùng trong danh sách
    public CourseEntity findTop1ByOrderByIdDesc();
}
