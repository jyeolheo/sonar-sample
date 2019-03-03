package com.sample.sonarsample.config;

import com.mongodb.WriteConcern;
import com.sample.sonarsample.filter.StubLoggingFilter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Configurations {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfig() {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.clear();
                converters.add(new StringHttpMessageConverter());
                converters.add(new MappingJackson2HttpMessageConverter());
            }

        };
    }

    @Bean
    public FilterRegistrationBean<StubLoggingFilter> loggingFilter(){
        FilterRegistrationBean<StubLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new StubLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public WriteConcernResolver writeConcernResolver() {
        return action -> WriteConcern.W1;
    }
}
