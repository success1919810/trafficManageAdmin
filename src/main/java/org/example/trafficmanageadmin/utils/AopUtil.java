package org.example.trafficmanageadmin.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.trafficmanageadmin.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class AopUtil {
    @Pointcut("execution(public * org.example.trafficmanageadmin.controller.*.*(..))")
    public void tempPointcut(){}
    @Around("tempPointcut()")
    private Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long startTime = System.currentTimeMillis();
        log.info("记录开始时间：{}",startTime);

        //放行程序
        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        String methon = joinPoint.getSignature().getName();
        log.info("程序{}执行时间为{}ms",methon,endTime-startTime);
        return proceed;
    }

//    @Before("tempPointcut()")
//    private void paramValid(JoinPoint joinPoint){
//        Object[] args = joinPoint.getArgs();
//        log.info("传入的参数为:{}",args);
//        for(Object arg:args){
//            if(arg == null){
//                throw new RuntimeException("参数不能为空");
//            }
//        }
//    } //非空参数校验

    @Before("execution(public * org.example.trafficmanageadmin.controller.LoginController.userRegister())")
    private void paramPerfect(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for(Object arg:args){
            if(arg instanceof User){
                User user = (User) arg;
                user.setStatus(1);
                user.setRole("user");
                user.setCreatedAt(LocalDateTime.now());
                user.setUpdatedAt(LocalDateTime.now());
            }
        }
    }
}
