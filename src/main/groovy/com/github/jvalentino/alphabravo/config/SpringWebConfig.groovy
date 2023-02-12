package com.github.jvalentino.alphabravo.config

import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView

/**
 * Another magical class required for JSPs and Spring Boot to work
 */
@EnableWebMvc
@Configuration
@ComponentScan(['com.github.jvalentino.alphabravo' ])
@Slf4j
@CompileDynamic
@SuppressWarnings(['UnnecessarySetter'])
class SpringWebConfig implements WebMvcConfigurer {

    @Override
    void addResourceHandlers(ResourceHandlerRegistry registry) {
        // https://stackoverflow.com/questions/25061237/spring-4-addresourcehandlers-not-resolving-the-static-resources
        registry.addResourceHandler('/resources/**')
                .addResourceLocations('classpath:/')
        registry.addResourceHandler('/webjars/**')
                .addResourceLocations('/webjars/')
    }

    @Bean
    InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver()
        viewResolver.setViewClass(JstlView)
        viewResolver.setPrefix('/WEB-INF/jsp/')
        viewResolver.setSuffix('.jsp')
        viewResolver
    }

}
