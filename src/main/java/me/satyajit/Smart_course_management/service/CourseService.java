package me.satyajit.Smart_course_management.service;

import me.satyajit.Smart_course_management.dto.course.CourseRequestDto;
import me.satyajit.Smart_course_management.dto.course.CourseResponseDto;
import me.satyajit.Smart_course_management.entities.Course;
import me.satyajit.Smart_course_management.entities.Instructor;
import me.satyajit.Smart_course_management.exception.DuplicateResourceException;
import me.satyajit.Smart_course_management.exception.ResourceNotFoundException;
import me.satyajit.Smart_course_management.exception.TitleAlreadyExistsException;
import me.satyajit.Smart_course_management.mapper.course.CourseMapper;
import me.satyajit.Smart_course_management.repository.CourseRepository;
import me.satyajit.Smart_course_management.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;
    private InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper,
                         InstructorRepository instructorRepository){
        this.courseRepository=courseRepository;
        this.courseMapper = courseMapper;
        this.instructorRepository=instructorRepository;
    }


    // Create Course
    public CourseResponseDto createCourse(CourseRequestDto requestCourse){

        boolean isTitleExist =
                courseRepository.existsByTitle(requestCourse.getTitle());
        if (isTitleExist){
            throw new TitleAlreadyExistsException("Tittle already exist, try another tittle");
        }

        Course courseToBeSaved =
                courseMapper.requestDtoToCourseEntityMapper(requestCourse);

       Course savedCourse = courseRepository.save(courseToBeSaved);

        return courseMapper.entityToCourseResponseDto(savedCourse);
    }

    // Read One Course By Id
    public CourseResponseDto getCourseById(Long id){

        Course existingCourse =
                courseRepository.findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        return courseMapper.entityToCourseResponseDto(existingCourse);
    }

    public List<CourseResponseDto> getAllCourses (){
        List<CourseResponseDto> coursesList = new ArrayList<>();
        List<Course> existingCourses = courseRepository.findAllByDeletedFalse();

        for (Course course: existingCourses){
            coursesList.add(courseMapper.entityToCourseResponseDto(course));
        }

        return coursesList;
    }


    public CourseResponseDto updateCourseById(Long id, CourseRequestDto requestCourse){

        Course existingCourse =
                courseRepository.findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        boolean isSameResource = courseMapper.isSameResource(existingCourse, requestCourse);

        if (isSameResource){
            throw new DuplicateResourceException("Request course info same as previous info");
        }

        if (!requestCourse.getTitle().equals(existingCourse.getTitle())){

            boolean isExistsTitle =
                    courseRepository.existsByTitleAndDeletedIsFalse(requestCourse.getTitle());
            if (isExistsTitle) throw new TitleAlreadyExistsException("Tittle Already exist");
        }

        existingCourse.setTitle(requestCourse.getTitle());
        existingCourse.setDescription(requestCourse.getDescription());
        existingCourse.setCapacity(requestCourse.getCapacity());
        existingCourse.setPrice(requestCourse.getPrice());
        existingCourse.setUpdatedAt(LocalDateTime.now());

        Course updatedCourse = courseRepository.save(existingCourse);

        return courseMapper.entityToCourseResponseDto(updatedCourse);
    }

    public String deleteCourseById(Long id){

        Course course =
                courseRepository.findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setDeleted(true);
        courseRepository.save(course);

        return "Course record deleted successfully";
    }

    public String assignInstructorToACourse(Long courseId, Long instructorId){

        Course existingCourse =
                courseRepository.findByIdAndDeletedIsFalse(courseId)
                        .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Instructor existingInstructor =
                instructorRepository.findByIdAndDeletedIsFalse(instructorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        if (existingInstructor.equals(existingCourse.getInstructor())){
            throw new DuplicateResourceException("Instructor " + existingInstructor.getName()+
                    " already registerd in this course "+existingCourse.getTitle());
        }

        existingCourse.setInstructor(existingInstructor);
        existingInstructor.getCourses().add(existingCourse);
        courseRepository.save(existingCourse);
        instructorRepository.save(existingInstructor);

        return "Instructor assign successfully";
    }
}
