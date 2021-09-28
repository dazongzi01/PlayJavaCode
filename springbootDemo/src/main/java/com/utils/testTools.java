package com.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-16 17:40
 **/
public class testTools {
    public static void main(String[] args) {
// 获取当前时间
        DateTime dateTime = cn.hutool.core.date.DateUtil.date();
        String startDate = "2021-09-16";
        DateTime startDateTime = DateUtil.parseDateTime(startDate + " 00:00:01");
        DateTime endDateTime = DateUtil.parse(startDate+ " 23:59:59");

        System.out.println(endDateTime.compareTo(startDateTime));
        System.out.println("当前时间"+dateTime);
        System.out.println("开始时间"+startDateTime);
        System.out.println("结束时间"+endDateTime);
    }
}
