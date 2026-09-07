package me.satyajit.Smart_course_management.mapper.Instructor;

import me.satyajit.Smart_course_management.dto.course.CourseResponseDto;
import me.satyajit.Smart_course_management.dto.instructor.InstructorRequestDto;
import me.satyajit.Smart_course_management.dto.instructor.InstructorResponseDto;
import me.satyajit.Smart_course_management.entities.Course;
import me.satyajit.Smart_course_management.entities.Instructor;
import me.satyajit.Smart_course_management.mapper.course.CourseMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InstructorMapper {

    private CourseMapper courseMapper;

    public InstructorMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public Instructor requestDtoToInstructorEntityMapper
            (InstructorRequestDto requestInstructor){

        Instructor instructor = new Instructor();

        instructor.setName(requestInstructor.getName());
        instructor.setEmail(requestInstructor.getEmail());
        instructor.setSpecialization(requestInstructor.getSpecialization());
        instructor.setCreatedAt(LocalDateTime.now());

        return instructor;
    }

    public InstructorResponseDto entityToInstructorResponseDtoMapper
            (Instructor instructor){
        InstructorResponseDto response = new InstructorResponseDto();

        response.setId(instructor.getId());
        response.setEmail(instructor.getEmail());
        response.setName(instructor.getName());
        response.setCreatedAt(instructor.getCreatedAt());
        response.setSpecialization(instructor.getSpecialization());

        List<CourseResponseDto> coursesList = new ArrayList<>();

        for (Course course: instructor.getCourses()){
            coursesList.add(courseMapper.entityToCourseResponseDto(course));
        }


        response.setCourses(coursesList);

        return response;
    }

    public boolean isSameDataToBeUpdate(
            InstructorRequestDto requestInstructor, Instructor existingInstructor
    ){

        return existingInstructor.getName().equals(requestInstructor.getName()) &&
                existingInstructor.getEmail().equals(requestInstructor.getEmail()) &&
                existingInstructor.getSpecialization().equals(requestInstructor.getSpecialization());
    }
}
