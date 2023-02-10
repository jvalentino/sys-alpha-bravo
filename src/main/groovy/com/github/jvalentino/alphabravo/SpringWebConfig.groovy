package com.github.jvalentino.alphabravo

import groovy.util.logging.Slf4j
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(["com.github.jvalentino.alphabravo" ])
//@EnableJpaRepositories("com.github.jvalentino.alphabravo.repo.*")
//@EntityScan("com.github.jvalentino.alphabravo.entity.*")
@Slf4j
class SpringWebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info('!!! addResourceHandlers')
        // https://stackoverflow.com/questions/25061237/spring-4-addresourcehandlers-not-resolving-the-static-resources
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}