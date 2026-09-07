package me.satyajit.Smart_course_management.exception;

public class AlreadyEnrolledException extends RuntimeException{

    public AlreadyEnrolledException(String message){
        super(message);
    }
}
