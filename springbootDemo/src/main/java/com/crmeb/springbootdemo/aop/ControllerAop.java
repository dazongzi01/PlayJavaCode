package com.crmeb.springbootdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

    @Before("webMethods()")
    private void visitBefore(JoinPoint joinPoint){
        System.out.println("AOP:知道你来了");
    }

    @After("webMethods()")
    private void visitAfter(){
        System.out.println("AOP:悄悄记录一笔");
    }
}
