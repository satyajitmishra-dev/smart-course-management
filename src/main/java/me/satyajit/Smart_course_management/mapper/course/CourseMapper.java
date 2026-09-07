package me.satyajit.Smart_course_management.mapper.course;

import me.satyajit.Smart_course_management.dto.course.CourseRequestDto;
import me.satyajit.Smart_course_management.dto.course.CourseResponseDto;
import me.satyajit.Smart_course_management.entities.Course;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CourseMapper {

    public Course requestDtoToCourseEntityMapper
            (CourseRequestDto requestCourse){

        Course course = new Course();

        course.setTitle(requestCourse.getTitle());
        course.setDescription(requestCourse.getDescription());
        course.setPrice(requestCourse.getPrice());
        course.setCapacity(requestCourse.getCapacity());
        course.setCreatedAt(LocalDateTime.now());

        return course;
    }

    public CourseResponseDto entityToCourseResponseDto(Course requestCourse){

        CourseResponseDto response = new CourseResponseDto();

        response.setId(requestCourse.getId());
        response.setTitle(requestCourse.getTitle());
        response.setDescription(requestCourse.getDescription());
        response.setPrice(requestCourse.getPrice());
        response.setCapacity(requestCourse.getCapacity());
        response.setCreatedAt(requestCourse.getCreatedAt());
        response.setInstructorName(requestCourse.getInstructor().getName());

        return response;
    }

    public boolean isSameResource(Course existingCourse, CourseRequestDto requestCourse) {
        return requestCourse.getTitle().equals(existingCourse.getTitle())
                && requestCourse.getDescription().equals(existingCourse.getDescription())
                && requestCourse.getCapacity() == existingCourse.getCapacity()
                && requestCourse.getPrice().equals(existingCourse.getPrice());
    }
}
