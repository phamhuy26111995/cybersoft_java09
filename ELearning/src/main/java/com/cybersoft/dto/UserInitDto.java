package com.cybersoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInitDto {
    private List<RoleDto> roles;
}
