package me.satyajit.Smart_course_management.controller;


import me.satyajit.Smart_course_management.dto.enrollment.EnrollmentRequestDto;
import me.satyajit.Smart_course_management.dto.enrollment.EnrollmentResponseDto;
import me.satyajit.Smart_course_management.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService=enrollmentService;
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> createEnrollment(
            @RequestBody EnrollmentRequestDto requestEnrollment
            ){

        EnrollmentResponseDto response = enrollmentService.createEnrollment(requestEnrollment);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDto>> getAllEnrollments(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDto> getEnrollmentById(@PathVariable Long id){

        EnrollmentResponseDto existingEnrollment =
                enrollmentService.getEnrollmentById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(existingEnrollment);
    }

    @PatchMapping("/cancel")
    public ResponseEntity<String> cancelEnrollmentById(@RequestParam Long id){

        String response = enrollmentService.cancelEnrollmentById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/completed")
    public ResponseEntity<String> completeEnrollmentById(@RequestParam Long id){

        String response = enrollmentService.completeEnrollmentById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
