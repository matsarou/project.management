package com.jrp.project.management.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger((this.getClass()));

    @Pointcut("within(com.jrp.project.management.controllers..**)")
    public void definePackagePointcuts() {
        //empty method to name the location specified in the pointcut
    }

    // Option 1
//    @Before("definePackagePointcuts()")
//    public void log() {
//        log.debug("----");
//    }
//    @After("definePackagePointcuts()")
//    public void logAfter(JoinPoint jp) {
//        log.debug("\n \n \n");
//        log.debug("****Before Method Execution**** \n {}.{} () with arguments[s] = {}",
//                jp.getSignature().getDeclaringTypeName(),
//                jp.getSignature().getName(),
//                Arrays.toString(jp.getArgs()));
//        log.debug("--------------\n");
//    }

    // Option 2
    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp) {
        log.debug("\n \n \n");
        log.debug("****Before Method Execution**** \n {}.{} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));
        log.debug("--------------\n");

        Object o = null;
        try {
            o = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        log.debug("****After Method Execution**** \n {}.{} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));
        log.debug("--------------\n");

        return o;
    }
}
