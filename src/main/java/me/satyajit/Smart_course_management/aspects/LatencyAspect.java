package me.satyajit.Smart_course_management.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class LatencyAspect {

    @Around("execution(* me.satyajit.Smart_course_management.service.*.*(..))")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

       Object result = joinPoint.proceed();

       long end = System.currentTimeMillis();

        System.out.println(
                joinPoint.getSignature().getName()
                        + " took "
                        + (end - startTime)
                        + " ms");

        return result;
    }
}
