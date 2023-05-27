package kr.co.smkpetclinicstudy.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionLoggingAspect {

    @Pointcut(value = "execution(* kr.co.smkpetclinicstudy.service.service.OwnerService.*(..))")
    public void ownerServiceMethods() {}

    @Pointcut(value = "execution(* kr.co.smkpetclinicstudy.service.service.PetService.*(..))")
    public void petServiceMethods() {}

    @Pointcut(value = "execution(* kr.co.smkpetclinicstudy.service.service.VetService.*(..))")
    public void vetServiceMethods() {}

    @Pointcut(value = "execution(* kr.co.smkpetclinicstudy.service.service.VisitService.*(..))")
    public void visitServiceMethods() {}

    @Around(value = "ownerServiceMethods() || petServiceMethods() || vetServiceMethods() || visitServiceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;

        log.info("Time taken for execution: {} ms", timeTaken);
        return proceed;
    }
}
