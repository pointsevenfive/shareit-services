package com.shareit.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RestLoggerAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Before("com.shareit.aspect.PointcutConfig.interceptController()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        logger.info("Incoming request - Source: {} Uri: {} Method: {}",
                request.getRemoteUser(),
                request.getRequestURI(),
                request.getMethod()
        );
        logger.debug("Invoked controller method: {} - Args: ",
                joinPoint.getSignature(),
                joinPoint.getArgs()
        );
    }
}
