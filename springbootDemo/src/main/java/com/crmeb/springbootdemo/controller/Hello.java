package com.crmeb.springbootdemo.controller;

import com.crmeb.springbootdemo.utils.TestTools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-09 17:31
 **/
@RestController
@RequestMapping("/test")
public class Hello {



    @Resource
    private TestTools testTools;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        System.out.println("来了老弟");
        String selfConfigForYml = testTools.getSelfConfig();
        return "你好帅哥 自定义配置是："+selfConfigForYml;
//        return "你好帅哥";
    }
}

