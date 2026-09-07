package me.satyajit.Smart_course_management.exception;

public class CourseCapacityReachedException extends RuntimeException{

    public CourseCapacityReachedException(String message){
        super(message);
    }
}
