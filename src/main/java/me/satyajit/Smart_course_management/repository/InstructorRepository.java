package me.satyajit.Smart_course_management.repository;


import me.satyajit.Smart_course_management.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    boolean existsByEmail(String email);

    Optional<Instructor> findByIdAndDeletedIsFalse(Long id);
}
