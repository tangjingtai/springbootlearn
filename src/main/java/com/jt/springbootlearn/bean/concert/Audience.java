package com.jt.springbootlearn.bean.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {

    @Pointcut("execution(* com.jt.springbootlearn.bean.concert.Performance.perform(..))")
    public void perform(){}

//    @Before("perform()")
    public void silenceCellPhones(){
        System.out.println("Silence cell phones.");
    }

//    @Before("perform()")
    public void takeSeaks(){
        System.out.println("Take seaks");
    }

//    @AfterReturning("perform()")
    public void applause(){
        System.out.println("CLAP CLAP CLAP ");
    }

//    @AfterThrowing("perform()")
    public void demandRefund(){
        System.out.println("Demand refund.");
    }

    @Around("perform()")
    public void watchPerformance(ProceedingJoinPoint joinPoint){
        System.out.println("watchPerformance...");
        try{
            silenceCellPhones();
            takeSeaks();
            joinPoint.proceed();
            applause();
        }
        catch (Throwable e) {
            demandRefund();
        }
    }
}
