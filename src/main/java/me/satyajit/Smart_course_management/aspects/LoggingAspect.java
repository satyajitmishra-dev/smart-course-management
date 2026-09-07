package me.satyajit.Smart_course_management.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* me.satyajit.Smart_course_management.service.*.*(..))")
   public void loggingExecutionMethodName(JoinPoint joinPoint){

        System.out.println("Executing : "+joinPoint.getSignature().getName());


   }
}
