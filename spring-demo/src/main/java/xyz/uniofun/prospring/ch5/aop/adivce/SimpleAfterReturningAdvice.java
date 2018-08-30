package xyz.uniofun.prospring.ch5.aop.adivce;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnVal, Method method,
                               Object[] args, Object target) throws Throwable {
        System.out.println("After " + method.getName() + " put down guitar");
    }
}
