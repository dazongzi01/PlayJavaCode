package com.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import java.util.Properties;

/**
 * 读取自定义yml配置文件 在更多calss中只有使用
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-11-11 11:43
 **/
public class ReadSelfYmlConfig {
    private static String PROPERTY_NAME = "application-dev.yml";

    public static Object getCommonYml(Object key){
        Resource resource = new ClassPathResource(PROPERTY_NAME);
        Properties properties = null;
        try {
            YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
            yamlFactory.setResources(resource);
            properties =  yamlFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return properties.get(key);
    }


    public static void main(String[] args) {
        System.out.println(getCommonYml("self-config.config1"));
        System.out.println(getCommonYml("self-config.config2"));
    }
}
