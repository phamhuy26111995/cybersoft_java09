package com.cybersoft.model.users;

import com.cybersoft.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsersModel {
    private Long total;
    private List<UserDto> users = new ArrayList<>();
}
