package com.cybersoft.service.impl;

import com.cybersoft.common.BaseService;
import com.cybersoft.dto.CourseContentDto;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.VideoDto;
import com.cybersoft.entity.CourseContentEntity;
import com.cybersoft.entity.CourseEntity;
import com.cybersoft.entity.Video;
import com.cybersoft.repository.CourseContentRepository;
import com.cybersoft.repository.CourseRepository;
import com.cybersoft.repository.VideoRepository;
import com.cybersoft.service.CourseContentService;
import com.cybersoft.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseContentServiceImpl extends BaseService implements CourseContentService {

    @Autowired
    private CourseContentRepository courseContentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<CourseContentDto> getByCourseId(Long courseId) {
        List<CourseContentEntity> entities = courseContentRepository.findByCourseId(courseId);
        List<CourseContentDto> dtos = new ArrayList<>();
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        for(CourseContentEntity entity : entities) {
            CourseContentDto dto = new CourseContentDto();
            modelMapper.map(entity, dto);
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public Long save(List<CourseContentDto> dtos) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        CourseEntity courseEntity = courseRepository.findById(13L).get();
        for(CourseContentDto dto : dtos) {
            CourseContentEntity entity = new CourseContentEntity();
            modelMapper.map(dto, entity);
            entity.setCourse(courseEntity);

            CourseContentEntity savedEntity = courseContentRepository.save(entity);

            for(VideoDto videoDto : dto.getVideoDtos()) {
                Video videoEntity = new Video();
                modelMapper.map(videoDto, videoEntity);

                videoEntity.setCourseContent(savedEntity);
                videoRepository.save(videoEntity);
            }
        }


        return null;
    }


}
