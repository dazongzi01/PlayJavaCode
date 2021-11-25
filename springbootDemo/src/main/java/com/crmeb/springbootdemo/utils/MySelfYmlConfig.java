package com.crmeb.springbootdemo.utils;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-11-11 15:03
 **/
@Configuration
public class MySelfYmlConfig {

    /**
     * 加载自定义yml配置文件,
     * 根目录为resources
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource[]{
                new ClassPathResource("application-dev.yml"),
                new ClassPathResource("application.yml")
        });
        pspc.setProperties(yaml.getObject());
        return pspc;
    }

}
