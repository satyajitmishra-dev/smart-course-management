package me.satyajit.Smart_course_management.controller;


import jakarta.validation.Valid;
import me.satyajit.Smart_course_management.dto.instructor.InstructorRequestDto;
import me.satyajit.Smart_course_management.dto.instructor.InstructorResponseDto;
import me.satyajit.Smart_course_management.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructor")
public class InstructorController {

    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService){
        this.instructorService=instructorService;
    }

    @PostMapping
    public ResponseEntity<InstructorResponseDto> createInstructor(
            @Valid @RequestBody InstructorRequestDto requestInstructor
            ){
        InstructorResponseDto createdInstructor =
                instructorService.createInstructor(requestInstructor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdInstructor);
    }

    @GetMapping
    public ResponseEntity<List<InstructorResponseDto>> getAllInstructor(){

        List<InstructorResponseDto> allExistingInstructor =
                instructorService.getAllInstructor();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(allExistingInstructor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponseDto> getInstructorById(@PathVariable Long id){

        InstructorResponseDto response =
                instructorService.getInstructorById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponseDto> UpdateInstructorById(
            @PathVariable Long id, @Valid @RequestBody InstructorRequestDto requestInstructor){

        InstructorResponseDto response =
                instructorService.updateInstructorById(id, requestInstructor);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable Long id){

        String response =
                instructorService.deleteInstructorById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
