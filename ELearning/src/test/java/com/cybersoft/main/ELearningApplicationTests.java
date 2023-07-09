package com.cybersoft.main;
import com.cybersoft.dto.SearchCourseDto;
import com.cybersoft.repository.CourseRepositoryCustom;
import com.cybersoft.repository.CourseRepositoryImpl;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ELearningApplicationTests {
    @Autowired
    private CourseService courseService;

    //testing function courseService.search(SearchCourseDto dto)
    @Test
    void testSearchCourse() {
        //Create SearchCourseDto object
        SearchCourseDto dto = new SearchCourseDto();
        dto.setUserId(1L);
        dto.setPageIndex(1);
        dto.setPageSize(10);
        dto.setCourseName("Java");
        assertEquals(1, courseService.search(dto).getContent());
    }
}
