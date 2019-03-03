package com.sample.sonarsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        //@formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(
                        newArrayList(
                                new ParameterBuilder()
                                    .name("authorization")
                                    .description("Description of authorization")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .required(true)
                                    .build()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sample.sonarsample.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
        //@formatter:on
    }

    private ApiInfo metaData() {
        return new ApiInfo(
            "Sonar API",
            "Spring Boot REST API for Sonar",
            "1.0",
            "Terms of service",
            new Contact("", "www.naver.com", "nkylion@naver.com"),
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0",
            Collections.emptyList());
    }
}
