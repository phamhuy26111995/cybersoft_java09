package com.cybersoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseContentBody {
    private Long courseId;
    private List<CourseContentDto> courseContentDtoList = new ArrayList<>();
}
