package me.satyajit.Smart_course_management.controller;


import jakarta.validation.Valid;
import me.satyajit.Smart_course_management.dto.student.StudentRequestDto;
import me.satyajit.Smart_course_management.dto.student.StudentResponseDto;
import me.satyajit.Smart_course_management.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto requestStudent
            ){

        StudentResponseDto createdStudent =
                studentService.createStudent(requestStudent);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(
            @PathVariable Long id
    ){
        StudentResponseDto existStudent =
                studentService.getStudentById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents(){

        List<StudentResponseDto> existStudents = studentService.getAllStudents();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existStudents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudentById(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDto requestStudent
    ){
        StudentResponseDto updatedStudent =
                studentService.updateStudentById(id, requestStudent);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedStudent);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(
            @PathVariable Long id
    ){
        String response = studentService.deleteStudentById(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(response);
    }
}
