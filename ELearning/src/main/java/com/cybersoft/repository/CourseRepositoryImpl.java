package com.cybersoft.repository;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.SearchCourseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<CourseDto> findCourseByCondition(SearchCourseDto searchCourseDto) {
        String queryString = "SELECT new " +
                "com.cybersoft.dto.CourseDto(c.id, " +
                "c.title, c.image, " +
                "c.lecturesCount," +
                "c.price,c.hourCount," +
                "c.description," +
                "c.discount,c.promotionPrice," +
                "c.viewCount," +
                "c.content," +
                "u.email, " +
                "u.fullname, category.title) FROM CourseEntity c JOIN c.users u JOIN c.category category WHERE 1 = 1 ";

        StringBuilder stringBuilder = new StringBuilder(queryString);

        if(StringUtils.isNotEmpty(searchCourseDto.getCourseName())) {
            stringBuilder.append(" AND c.title LIKE :courseName");
        }

        if(StringUtils.isNotEmpty(searchCourseDto.getCourseUserName())) {
            stringBuilder.append(" AND u.fullname LIKE :userCourseName");
        }

        Query query = entityManager.createQuery(stringBuilder.toString());

        if(StringUtils.isNotEmpty(searchCourseDto.getCourseName())) {
            query.setParameter("courseName", "%" + searchCourseDto.getCourseName() + "%");
        }

        if(StringUtils.isNotEmpty(searchCourseDto.getCourseUserName())) {
            query.setParameter("userCourseName" , "%" + searchCourseDto.getCourseUserName() + "%");
        }

        int offset = (searchCourseDto.getPageIndex() -1) * searchCourseDto.getPageSize();

        query.setFirstResult(offset);
        query.setMaxResults(searchCourseDto.getPageSize());

        return query.getResultList();
    }
}
