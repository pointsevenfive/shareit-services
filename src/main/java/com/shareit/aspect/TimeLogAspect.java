package com.shareit.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Around("com.shareit.aspect.PointcutConfig.timedRepositoryCall()")
    public Object time(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object value;

        try {
            value = proceedingJoinPoint.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;

            logger.info(
                    "{}.{} took {} ms",
                    proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(),
                    proceedingJoinPoint.getSignature().getName(),
                    duration);
        }

        return value;
    }
}
