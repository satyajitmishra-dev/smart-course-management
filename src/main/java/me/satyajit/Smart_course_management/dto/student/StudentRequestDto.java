package me.satyajit.Smart_course_management.dto.student;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentRequestDto {

    @NotBlank(message = "Name cannot be blank/empty")
    @Size(min = 3, message = "Name should be 3 character long")
    private String name;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email cannot be blank/empty")

    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Enter a valid mobile number")

    private String mobileNo;

}
