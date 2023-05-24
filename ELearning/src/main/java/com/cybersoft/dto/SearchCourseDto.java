package com.cybersoft.dto;

import com.cybersoft.common.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCourseDto extends BaseDTO {
    private Long userId;
    private String courseName;
    private String courseUserName;
}
