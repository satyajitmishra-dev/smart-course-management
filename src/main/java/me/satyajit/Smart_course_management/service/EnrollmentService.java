package me.satyajit.Smart_course_management.service;

import jakarta.transaction.Transactional;
import me.satyajit.Smart_course_management.dto.enrollment.EnrollmentRequestDto;
import me.satyajit.Smart_course_management.dto.enrollment.EnrollmentResponseDto;
import me.satyajit.Smart_course_management.entities.Course;
import me.satyajit.Smart_course_management.entities.Enrollment;
import me.satyajit.Smart_course_management.entities.Student;
import me.satyajit.Smart_course_management.enums.EnrollmentStatus;
import me.satyajit.Smart_course_management.exception.AlreadyEnrolledException;
import me.satyajit.Smart_course_management.exception.CourseCapacityReachedException;
import me.satyajit.Smart_course_management.exception.ResourceNotFoundException;
import me.satyajit.Smart_course_management.mapper.enrollment.EnrollmentMapper;
import me.satyajit.Smart_course_management.repository.CourseRepository;
import me.satyajit.Smart_course_management.repository.EnrollmentRepository;
import me.satyajit.Smart_course_management.repository.StudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;
    private EnrollmentMapper enrollmentMapper;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             EnrollmentMapper enrollmentMapper,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentMapper = enrollmentMapper;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public EnrollmentResponseDto createEnrollment(EnrollmentRequestDto requestEnrollment){

        Long studentId = requestEnrollment.getStudentId();
        Long courseId = requestEnrollment.getCourseId();

        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course existingCourse = courseRepository
                .findByIdAndDeletedIsFalse(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        boolean isAlreadyEnrolled =
                enrollmentRepository.existsByStudentAndCourse(existingStudent, existingCourse);
        if (isAlreadyEnrolled){
            throw new AlreadyEnrolledException("Already enrolled");
        }

        long activeEnrollments =
                enrollmentRepository
                        .countByCourseAndStatus(existingCourse, EnrollmentStatus.ACTIVE);

        if (activeEnrollments >= existingCourse.getCapacity()){
            throw new CourseCapacityReachedException("Course capacity reached");
        }

        Enrollment enrollmentToBeCreated =
                enrollmentMapper
                        .requestDtoToEnrollmentEntityMapper(existingStudent, existingCourse);

        Enrollment createdEnrollment = enrollmentRepository.save(enrollmentToBeCreated);

        return enrollmentMapper.entityToEnrollmentResponseDto(createdEnrollment);
    }

    @Transactional
    public EnrollmentResponseDto getEnrollmentById(Long id){

        Enrollment existingEnrollment =
                enrollmentRepository.findByIdAndStatus(id, EnrollmentStatus.ACTIVE)
                        .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        return enrollmentMapper.entityToEnrollmentResponseDto(existingEnrollment);
    }

    @Transactional
    public List<EnrollmentResponseDto> getAllEnrollments(){

        Sort sort = Sort.by("id").descending();

        List<Enrollment> existingEnrollments =
                enrollmentRepository.findAll(sort);

        List<EnrollmentResponseDto> enrollmentResponse = new ArrayList<>();

        for (Enrollment enrollment: existingEnrollments){
            enrollmentResponse.add(enrollmentMapper
                    .entityToEnrollmentResponseDto(enrollment));
        }

        return enrollmentResponse;
    }

    @Transactional
    public String cancelEnrollmentById(Long id){

        Enrollment existignEnrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        if (existignEnrollment.getStatus().equals(EnrollmentStatus.CANCELLED)){
            throw new RuntimeException("Enrollment already CANCELED");
        }

        existignEnrollment.setStatus(EnrollmentStatus.CANCELLED);
        enrollmentRepository.save(existignEnrollment);

        return "Enrollment CANCELED";
    }

    @Transactional
    public String completeEnrollmentById(Long id){
        Enrollment existignEnrollment =
                enrollmentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        if (existignEnrollment.getStatus().equals(EnrollmentStatus.COMPLETED)){
            throw new RuntimeException("Enrollment already COMPLETED");
        } if (existignEnrollment.getStatus().equals(EnrollmentStatus.CANCELLED)){
            throw new RuntimeException("Enrollment already CANCELED");
        }

        existignEnrollment.setStatus(EnrollmentStatus.COMPLETED);
        enrollmentRepository.save(existignEnrollment);

        return "Enrollment COMPLETED";
    }
}
