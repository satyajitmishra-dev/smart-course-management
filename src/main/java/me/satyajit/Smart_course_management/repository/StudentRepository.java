package me.satyajit.Smart_course_management.repository;

import me.satyajit.Smart_course_management.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail (String email);
    Optional<Student>findByEmail(String email);

    List<Student> findAllByDeletedFalse();
    Optional<Student>findByIdAndDeletedIsFalse(Long id);
}
