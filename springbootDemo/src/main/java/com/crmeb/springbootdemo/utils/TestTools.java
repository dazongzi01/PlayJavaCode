package com.crmeb.springbootdemo.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-16 17:40
 **/
@Component
public class TestTools {

    @Value("${self-config.config1}")
    public String config1;

    @Value("${self-config.config2}")
    public String config2;


    public String getSelfConfig(){
        return config1;
    }

//    public static void main(String[] args) {
//// 获取当前时间
//        DateTime dateTime = cn.hutool.core.date.DateUtil.date();
//        String startDate = "2021-09-16";
//        DateTime startDateTime = DateUtil.parseDateTime(startDate + " 00:00:01");
//        DateTime endDateTime = DateUtil.parse(startDate+ " 23:59:59");
//
//        System.out.println(endDateTime.compareTo(startDateTime));
//        System.out.println("当前时间"+dateTime);
//        System.out.println("开始时间"+startDateTime);
//        System.out.println("结束时间"+endDateTime);
//    }
}
