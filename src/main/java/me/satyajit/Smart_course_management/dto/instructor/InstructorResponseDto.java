package me.satyajit.Smart_course_management.dto.instructor;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.satyajit.Smart_course_management.dto.course.CourseResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InstructorResponseDto {

    private Long id;
    private String name;
    private String email;
    private String specialization;
    private LocalDateTime createdAt;

    List<CourseResponseDto> courses = new ArrayList<>();
}
