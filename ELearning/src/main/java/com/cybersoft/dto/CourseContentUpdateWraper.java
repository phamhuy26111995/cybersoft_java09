package com.cybersoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseContentUpdateWraper {
    private CourseContentBody courseContentBody;
    private CourseContentDeleteDto courseContentDeleteDto;
}
