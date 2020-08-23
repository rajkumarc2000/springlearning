package com.example.weebservices.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Set DEFAULT_CONSUMES_PRODUCES = new HashSet<>(Arrays.asList("applicatin/json","application/xml"));

    public static Contact DEFAULT_CONTACT = new Contact("Rajkumar Chandrasekaran", "url",
            "rajkumar.chandrasekaran@cognizant.com");

    public static ApiInfo apiInfo= new ApiInfo("APi Documentation","APi documentation","1.0","urn:tos",DEFAULT_CONTACT,
                                                "Apache 2.0","www.apache.org/license.html",new ArrayList<>());
    
    @Bean
    public Docket api(){

       return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .produces(DEFAULT_CONSUMES_PRODUCES)
            .consumes(DEFAULT_CONSUMES_PRODUCES);

    }
}