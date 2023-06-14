package com.cybersoft.repository;

import com.cybersoft.dto.UserDto;
import com.cybersoft.dto.UserSearchDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<UserDto> findUserByCondition(UserSearchDto searchDto) {
        String queryString = "SELECT " +
                "new com.cybersoft.dto.UserDto(u.id, u.fullname, u.email,u.avatar, r.description) " +
                "FROM User u JOIN Role r ON u.roleId = r.id WHERE r.name NOT LIKE '%ADMIN%' ";
        StringBuilder stringBuilder = new StringBuilder(queryString);

        setConditionQueryString(stringBuilder, searchDto);

        Query query = entityManager.createQuery(stringBuilder.toString());

        setParamForQuery(query, searchDto);

        int offset = (searchDto.getPageIndex() - 1) * searchDto.getPageSize();

        query.setFirstResult(offset);
        query.setMaxResults(searchDto.getPageSize());

        return query.getResultList();
    }

    @Override
    public Long findTotalResult(UserSearchDto searchDto) {
        String queryString = "SELECT " +
                "COUNT(u) " +
                "FROM User u JOIN Role r ON u.roleId = r.id WHERE r.name NOT LIKE '%ADMIN%' ";
        StringBuilder queryBuilder = new StringBuilder(queryString);
        setConditionQueryString(queryBuilder, searchDto);

        Query query = entityManager.createQuery(queryBuilder.toString());

        setParamForQuery(query, searchDto);

        return query.getSingleResult() != null ? (Long) query.getSingleResult() : 0;
    }

    private void setConditionQueryString(StringBuilder queryBuilder, UserSearchDto searchDto) {
        if(StringUtils.isNotEmpty(searchDto.getUserName())) {
            queryBuilder.append(" AND u.fullname LIKE :userName ");
        }

        if(StringUtils.isNotEmpty(searchDto.getUserEmail())) {
            queryBuilder.append(" AND u.email LIKE :userEmail ");
        }

    }

    private void setParamForQuery(Query query, UserSearchDto searchDto) {
        if(StringUtils.isNotEmpty(searchDto.getUserName())) {
            query.setParameter("userName", "%" + searchDto.getUserName() + "%");
        }

        if(StringUtils.isNotEmpty(searchDto.getUserEmail())) {
            query.setParameter("userEmail", "%" + searchDto.getUserEmail() + "%");
        }
    }


}
