package com.cybersoft.repository;

import com.cybersoft.dto.UserDto;
import com.cybersoft.dto.UserSearchDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> findUserByCondition(UserSearchDto searchDto);

    Long findTotalResult(UserSearchDto searchDto);
}
