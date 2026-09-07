package me.satyajit.Smart_course_management.service;


import me.satyajit.Smart_course_management.dto.instructor.InstructorRequestDto;
import me.satyajit.Smart_course_management.dto.instructor.InstructorResponseDto;
import me.satyajit.Smart_course_management.entities.Instructor;
import me.satyajit.Smart_course_management.exception.DuplicateResourceException;
import me.satyajit.Smart_course_management.exception.EmailAlreadyExistsException;
import me.satyajit.Smart_course_management.exception.ResourceNotFoundException;
import me.satyajit.Smart_course_management.mapper.Instructor.InstructorMapper;
import me.satyajit.Smart_course_management.repository.CourseRepository;
import me.satyajit.Smart_course_management.repository.InstructorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {


    private InstructorRepository instructorRepository;
    private InstructorMapper instructorMapper;

    public InstructorService(InstructorRepository instructorRepository,
                             InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }


    public InstructorResponseDto createInstructor
            (InstructorRequestDto requestInstructor){

        boolean isEmailExists =
                instructorRepository.existsByEmail(requestInstructor.getEmail());
        if (isEmailExists){
            throw new EmailAlreadyExistsException("Email already registered before");
        }

        Instructor instructorToBeCreated =
                instructorMapper.requestDtoToInstructorEntityMapper(requestInstructor);

        Instructor createdInstructor =
                instructorRepository.save(instructorToBeCreated);


        return instructorMapper.entityToInstructorResponseDtoMapper(createdInstructor);
    }

    public InstructorResponseDto getInstructorById(Long id){

        Instructor existingInstructor =
                instructorRepository.findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        return instructorMapper.entityToInstructorResponseDtoMapper(existingInstructor);
    }

    public List<InstructorResponseDto> getAllInstructor(){

        Sort sort = Sort.by("name");

        List<Instructor> allExistingInstructors =
                instructorRepository.findAll(sort);

        List<InstructorResponseDto> responseInstructor = new ArrayList<>();

        for (Instructor instructor : allExistingInstructors){
            responseInstructor.add(
                    instructorMapper.entityToInstructorResponseDtoMapper(instructor)
            );
        }

        return responseInstructor;
    }

    public InstructorResponseDto updateInstructorById(
            Long id, InstructorRequestDto requestInstructor){

        Instructor existingInstructor =
                instructorRepository.findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Instructor Not Found"));

        if (! requestInstructor.getEmail().equals(existingInstructor.getEmail())){

            if (instructorRepository.existsByEmail(requestInstructor.getEmail())){
                throw new EmailAlreadyExistsException("Email already used");
            }
        }

        boolean isSameDataToBeUpdate =
                instructorMapper.isSameDataToBeUpdate(requestInstructor, existingInstructor);

        if (isSameDataToBeUpdate){
            throw new DuplicateResourceException("Updatation info same as existing info");
        }

        existingInstructor.setName(requestInstructor.getName());
        existingInstructor.setEmail(requestInstructor.getEmail());
        existingInstructor.setSpecialization(requestInstructor.getSpecialization());

        Instructor updatedInstructor =
                instructorRepository.save(existingInstructor);

        return instructorMapper.entityToInstructorResponseDtoMapper(updatedInstructor);
    }

    public String deleteInstructorById(Long id){

        Instructor existingInstructor =
                instructorRepository.findByIdAndDeletedIsFalse(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        existingInstructor.setDeleted(true);
        instructorRepository.save(existingInstructor);

        return "Instructor record deleted successfully";
    }


}
