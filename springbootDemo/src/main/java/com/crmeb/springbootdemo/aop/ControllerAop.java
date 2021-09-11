package com.crmeb.springbootdemo.aop;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-09 18:19
 **/
@Component
@Aspect
public class ControllerAop{

    // 切入点 相当于包扫描
//    @Pointcut("execution(public * com.crmeb.springbootdemo..*.*(..))")
    @Pointcut("execution(public * com.crmeb.springbootdemo.controller..*.*(..))")
    public void webMethods(){

    }

//    @Around("webMethods()")
//    public Object doAround(ProceedingJoinPoint proceedingJoinPoint){
//        return proceedingJoinPoint;
//    }

    @Before("webMethods()")
    private void visitBefore(JoinPoint joinPoint){
        System.out.println("AOP:知道你来了");
    }

    @After("webMethods()")
    private void visitAfter(){
        System.out.println("AOP:悄悄记录一笔");
    }
}
