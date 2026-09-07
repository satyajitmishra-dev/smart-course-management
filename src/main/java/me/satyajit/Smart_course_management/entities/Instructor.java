package me.satyajit.Smart_course_management.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String specialization;
    // Still teach in course/academic session
    private Boolean deleted = false;
    // Joined at
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses = new ArrayList<>();
}
