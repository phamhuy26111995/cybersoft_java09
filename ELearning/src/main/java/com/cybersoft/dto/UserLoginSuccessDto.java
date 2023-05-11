package com.cybersoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginSuccessDto {
    private String token;
    private String fullname;
    private String avatar;
    private String email;
}
