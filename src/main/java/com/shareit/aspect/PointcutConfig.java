package com.shareit.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutConfig {

    @Pointcut("@annotation(com.shareit.aspect.Timed) && execution(public * *(..))")
    public void timedRepositoryCall() {}

    @Pointcut("@annotation(com.shareit.aspect.Restrict) && execution(public * *(..))")
    public void restrictedCall() {}

    @Pointcut("@annotation(com.shareit.aspect.RestLogger) && execution(public * *(..))")
    public void interceptController() {}
}
