package me.satyajit.Smart_course_management.dto.student;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class StudentResponseDto {

    private Long id;

    private String name;

    private String email;
    private String mobileNo;
    private LocalDateTime createdAt;

}
