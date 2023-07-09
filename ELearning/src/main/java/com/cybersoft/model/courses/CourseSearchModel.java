package com.cybersoft.model.courses;

import com.cybersoft.dto.CourseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseSearchModel {
    private long total;
    private List<CourseDto> content;
}
