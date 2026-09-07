package me.satyajit.Smart_course_management.mapper.enrollment;

import me.satyajit.Smart_course_management.dto.enrollment.EnrollmentRequestDto;
import me.satyajit.Smart_course_management.dto.enrollment.EnrollmentResponseDto;
import me.satyajit.Smart_course_management.entities.Course;
import me.satyajit.Smart_course_management.entities.Enrollment;
import me.satyajit.Smart_course_management.entities.Student;
import me.satyajit.Smart_course_management.enums.EnrollmentStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EnrollmentMapper {

    public Enrollment requestDtoToEnrollmentEntityMapper
            (Student requestStudent, Course requestCourse){

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(requestStudent);
        enrollment.setCourse(requestCourse);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        return enrollment;
    }

    public EnrollmentResponseDto entityToEnrollmentResponseDto
            (Enrollment requestEnrollment){

        EnrollmentResponseDto response = new EnrollmentResponseDto();

        response.setId(requestEnrollment.getId());
        response.setCourseTitle(requestEnrollment.getCourse().getTitle());
        response.setStudentName(requestEnrollment.getStudent().getName());
        response.setEnrollmentDate(requestEnrollment.getEnrollmentDate());
        response.setEnrollmentStatus(requestEnrollment.getStatus());

        return response;
    }
}
