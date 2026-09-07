package me.satyajit.Smart_course_management.exception;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String message ){
        super(message);
    }
}
