package me.satyajit.Smart_course_management.dto.course;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CourseRequestDto {

    @NotBlank(message = "Course title cannot be empty")
    @Size(min = 3, message = "Course title should be 3 character long")
    private String title;
    @NotBlank(message = "Course description cannot be empty")
    @Size(min = 3, message = "Course description should be 3 character long")
    private String description;

    @NotNull(message = "Please enter the price")
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull(message = "Please enter the capacity")
    @Min(value = 1, message = "Course capacity must be > 1")
    private int capacity;
}
