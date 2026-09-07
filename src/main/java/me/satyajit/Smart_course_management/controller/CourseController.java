package me.satyajit.Smart_course_management.controller;


import jakarta.validation.Valid;
import me.satyajit.Smart_course_management.dto.course.CourseRequestDto;
import me.satyajit.Smart_course_management.dto.course.CourseResponseDto;
import me.satyajit.Smart_course_management.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }


    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(
            @Valid @RequestBody CourseRequestDto requestCourse
    ){

        CourseResponseDto savedCourse = courseService.createCourse(requestCourse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id){

        CourseResponseDto existingCourse = courseService.getCourseById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingCourse);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getAllCourse(){

        List<CourseResponseDto> existingCourses = courseService.getAllCourses();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingCourses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> updateCourseById(
            @PathVariable Long id, @Valid @RequestBody CourseRequestDto requestCourse){

        CourseResponseDto updatedCourse = courseService.updateCourseById(id, requestCourse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCourse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long id){

        String response = courseService.deleteCourseById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/{courseId}/assign-instructor")
    public ResponseEntity<String> assignInstructorToACourse(
            @PathVariable Long courseId, @RequestParam Long instructorId
    ){

        String response = courseService.assignInstructorToACourse(courseId, instructorId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
