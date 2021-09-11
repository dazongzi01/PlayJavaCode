package com.crmeb.springbootdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: springboot-demo
 * @author: 大粽子
 * @create: 2021-09-09 18:01
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${swagger.basic.enable}")
    private boolean swaggerEnabled;



    @Bean
    public Docket createMyApi(){
        return new Docket(DocumentationType.SWAGGER_2).enable(swaggerEnabled).apiInfo(DemoApiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.crmeb"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo DemoApiInfo(){
        return new ApiInfoBuilder().title("CRMEB DOC").description("我们的Java产品怎么样")
                .version("0.1").build();
    }
}
