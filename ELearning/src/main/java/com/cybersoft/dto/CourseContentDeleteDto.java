package com.cybersoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseContentDeleteDto {
    private List<Long> deleteCourseContent = new ArrayList<>();
    private List<Integer> deleteVideo = new ArrayList<>();
}
