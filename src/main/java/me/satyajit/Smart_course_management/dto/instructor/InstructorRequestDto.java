package me.satyajit.Smart_course_management.dto.instructor;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorRequestDto {

    @NotBlank(message = "Name required")
    @Size(min = 3, message = "Name must be 3 character long")
    private String name;

    @NotBlank(message = "Email required")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Specialization required")
    @Size(min = 3, message = "Specialization must be 3 character long")
    private String specialization;
}
