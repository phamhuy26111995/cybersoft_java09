package com.cybersoft.repository;

import com.cybersoft.common.IdentifyUser;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.SearchCourseDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

        if(IdentifyUser.getRolePrincipal().equals("ROLE_TEACHER")) {
            stringBuilder.append(" AND u.id = :userId");
        }

        Query query = entityManager.createQuery(createQuery(stringBuilder, searchCourseDto));

        setParameter(query, searchCourseDto);

        int offset = (searchCourseDto.getPageIndex() -1) * searchCourseDto.getPageSize();

        query.setFirstResult(offset);
        query.setMaxResults(searchCourseDto.getPageSize());

        return query.getResultList();
    }

    @Override
    public long countCourseByCondition(SearchCourseDto searchCourseDto) {
        String queryString = "SELECT COUNT(c.id) FROM CourseEntity c JOIN c.users u JOIN c.category category WHERE 1 = 1 ";

        StringBuilder stringBuilder = new StringBuilder(queryString);

        Query query = entityManager.createQuery(createQuery(stringBuilder, searchCourseDto));


        return (long) query.getSingleResult();
    }

    private String createQuery(StringBuilder stringBuilder, SearchCourseDto searchCourseDto) {
        if(StringUtils.isNotEmpty(searchCourseDto.getCourseName())) {
            stringBuilder.append(" AND c.title LIKE :courseName");
        }

        if(StringUtils.isNotEmpty(searchCourseDto.getCourseUserName())) {
            stringBuilder.append(" AND u.fullname LIKE :userCourseName");
        }

        return stringBuilder.toString();
    }

    private void setParameter(Query query, SearchCourseDto searchCourseDto) {
        if(StringUtils.isNotEmpty(searchCourseDto.getCourseName())) {
            query.setParameter("courseName", "%" + searchCourseDto.getCourseName() + "%");
        }

        if(StringUtils.isNotEmpty(searchCourseDto.getCourseUserName())) {
            query.setParameter("userCourseName", "%" + searchCourseDto.getCourseUserName() + "%");
        }
        
        if(IdentifyUser.getRolePrincipal().equals("ROLE_TEACHER")) {
            query.setParameter("userId", IdentifyUser.getIdPrincipal());
        }
    }
}
