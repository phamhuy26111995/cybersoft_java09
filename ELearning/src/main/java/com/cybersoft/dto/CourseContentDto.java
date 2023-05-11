package com.cybersoft.dto;

import com.cybersoft.entity.Video;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseContentDto {
    private Long id;
    private String content;
    private List<VideoDto> videoDtos = new ArrayList<>();
}
