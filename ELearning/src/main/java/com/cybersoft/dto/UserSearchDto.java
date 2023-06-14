package com.cybersoft.dto;

import com.cybersoft.common.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchDto extends BaseDTO {
    private String userName;
    private String userEmail;
}
