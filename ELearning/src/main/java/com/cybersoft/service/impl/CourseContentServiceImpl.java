package com.cybersoft.service.impl;

import com.cybersoft.common.BaseService;
import com.cybersoft.dto.*;
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
    public Long save(CourseContentBody body) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        CourseEntity courseEntity = courseRepository.findById(body.getCourseId()).orElse(null);

        if(courseEntity == null) return null;

        for(CourseContentDto dto : body.getCourseContentDtoList()) {
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

    @Override
    public void delete(CourseContentDeleteDto dto) {
        for(Integer videoId : dto.getDeleteVideo()) {
            videoRepository.deleteById(videoId);
        }

        for(Long courseContentId : dto.getDeleteCourseContent()) {
            courseContentRepository.deleteById(courseContentId);
        }
    }


}
