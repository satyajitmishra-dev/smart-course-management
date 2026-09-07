package me.satyajit.Smart_course_management.repository;


import me.satyajit.Smart_course_management.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByTitle(String title);
    Optional<Course> findByIdAndDeletedIsFalse(Long id);
    List<Course> findAllByDeletedFalse();
    boolean existsByTitleAndDeletedIsFalse(String tittle);
}
