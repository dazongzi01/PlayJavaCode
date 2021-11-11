package com.crmeb.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan(basePackages = {"com.crmeb.*.dao","com.crmeb.*.*.dao"})
public class SpringbootDemoApplication {
//public class SpringbootDemoApplication extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SpringbootDemoApplication.class);
//    }
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
        System.out.println("Java Server Started !");
    }

//    public static String name = "muyuan";


//    public static void main(String[] args) {
//        List<SystemAdmin> admins = new ArrayList<>();
//        SystemAdmin admin1 = new SystemAdmin();
//        admin1.setAccount("zhangsan1");
//        final String account = admin1.getAccount();
//        admin1.setCreateTime(new Date());
//        SystemAdmin admin2 = new SystemAdmin();
//        admin2.setAccount("zhangsan2");
//        SystemAdmin admin3 = new SystemAdmin();
//        admin3.setAccount("zhangsan3");
//        admins.add(admin1);
//        admins.add(admin2);
//        admins.add(admin3);
//
//        System.out.println(admins.toString());
//
////        name = "liaofei";
//    }

}
