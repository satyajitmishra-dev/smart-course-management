package me.satyajit.Smart_course_management.dto.course;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponseDto {

    private Long id;

    private String title;
    private String description;
    private BigDecimal price;
    private int capacity;
    private String instructorName;
    private LocalDateTime createdAt;
}
