package me.satyajit.Smart_course_management.service;

import me.satyajit.Smart_course_management.dto.student.StudentRequestDto;
import me.satyajit.Smart_course_management.dto.student.StudentResponseDto;
import me.satyajit.Smart_course_management.entities.Student;
import me.satyajit.Smart_course_management.exception.DuplicateResourceException;
import me.satyajit.Smart_course_management.exception.EmailAlreadyExistsException;
import me.satyajit.Smart_course_management.exception.ResourceNotFoundException;
import me.satyajit.Smart_course_management.repository.StudentRepository;
import org.hibernate.query.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }


    public StudentResponseDto createStudent(StudentRequestDto requestStudent){

       boolean isExistingEmail
               = studentRepository.existsByEmail(requestStudent.getEmail());

       if (isExistingEmail) {
               throw new EmailAlreadyExistsException(
                       "Email " + requestStudent.getEmail() + " already exists"
               );
       }

            Student reqStudent = mapToStudentEntity(requestStudent);
            Student savedStudent = studentRepository.save(reqStudent);
             return mapToStudentResponse(savedStudent);

    }

    public StudentResponseDto getStudentById(Long id){

       Student existStudent
               = studentRepository.findByIdAndDeletedIsFalse(id)
               .orElseThrow(() ->
                       new ResourceNotFoundException("Student not found"));

        return mapToStudentResponse(existStudent);

    }

    public List<StudentResponseDto> getAllStudents(){

        List<StudentResponseDto> response = new ArrayList<>();

        List<Student> existingStudents = studentRepository.findAllByDeletedFalse();

        for (Student student : existingStudents){
            response.add(mapToStudentResponse(student));
        }

        return response;
    }

    public StudentResponseDto updateStudentById(
            Long id, StudentRequestDto requestStudent
    ){
        Optional<Student> existingStudent =
                studentRepository.findByIdAndDeletedIsFalse(id);
        if (existingStudent.isEmpty()){
            throw new ResourceNotFoundException("Student not found");
        }
        boolean isDuplicateResource =
                isDuplicateResource(existingStudent.get(), requestStudent);

        if (isDuplicateResource){
            throw new DuplicateResourceException("your updatation data same as existing data");
        }

        Student studentToBeUpdated = existingStudent.get();

        if (!requestStudent.getEmail().equals(studentToBeUpdated.getEmail())){
            boolean isEmailExist =
                    studentRepository.existsByEmail(requestStudent.getEmail());
            if (isEmailExist)
                throw new EmailAlreadyExistsException("Email already exist try with different Email");
        }

        studentToBeUpdated.setName(requestStudent.getName());
        studentToBeUpdated.setMobileNo(requestStudent.getMobileNo());
        studentToBeUpdated.setEmail(requestStudent.getEmail());
        studentToBeUpdated.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = studentRepository.save(studentToBeUpdated);

        return mapToStudentResponse(updatedStudent);
    }

    public String deleteStudentById(Long id){

        Student student =
                studentRepository
                        .findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Student not found"));

        student.setDeleted(true);
        studentRepository.save(student);

        return "Student record deleted successfully";
    }


    private Student mapToStudentEntity(StudentRequestDto requestStudent){
        Student student = new Student();

        student.setName(requestStudent.getName());
        student.setEmail(requestStudent.getEmail());
        student.setMobileNo(requestStudent.getMobileNo());
        student.setDeleted(false);
        student.setCreatedAt(LocalDateTime.now());

        return student;
    }

    private StudentResponseDto mapToStudentResponse(Student student){
        StudentResponseDto responseDto = new StudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setEmail(student.getEmail());
        responseDto.setMobileNo(student.getMobileNo());
        responseDto.setCreatedAt(student.getCreatedAt());

        return responseDto;
    }

    private boolean isDuplicateResource(
            Student existingStudent, StudentRequestDto requestStudent
    ){

        if( requestStudent.getEmail().equals(existingStudent.getEmail()) &&
                  requestStudent.getMobileNo().equals(existingStudent.getMobileNo()) &&
                  requestStudent.getName().equals(existingStudent.getName())
                ){
            return true;
        }
        return false;
    }


}
