package me.satyajit.Smart_course_management.repository;

import me.satyajit.Smart_course_management.entities.Course;
import me.satyajit.Smart_course_management.entities.Enrollment;
import me.satyajit.Smart_course_management.entities.Student;
import me.satyajit.Smart_course_management.enums.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentAndCourse(Student student, Course course);

    long countByCourseAndStatus(Course course, EnrollmentStatus status);

    Optional<Enrollment> findByIdAndStatus(Long id, EnrollmentStatus status);

}
