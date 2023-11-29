package com.wangqin.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        //构建在线API概要对象
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("com.wangqin.stock.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        //网站联系方式
        Contact contact = new Contact("Nick Wang", "", "e0012720@u.nus.edu");
        return new ApiInfoBuilder()
                .title("StockHub - Online API Documentation")//文档标题
                .description("This is an online API document that facilitates front-end and back-end developers to quickly understand the requirements for developing APIs.") //文档描述信息
                .contact(contact) //站点联系人相关信息
                .version("1.0.0") //文档版本
                .build();
    }
}