package com.crmeb.springbootdemo.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-09 17:31
 **/
@RestController
public class Hello {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String hello(){
        System.out.println("来了老弟");
        return "你好帅哥";
    }
}

