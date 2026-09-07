package me.satyajit.Smart_course_management.dto.enrollment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.satyajit.Smart_course_management.enums.EnrollmentStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EnrollmentResponseDto {

    private Long id;

    private String studentName;
    private String courseTitle;
    private EnrollmentStatus enrollmentStatus;
    private LocalDateTime enrollmentDate;
}
