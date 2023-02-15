package com.github.jvalentino.alphabravo.config

import groovy.transform.CompileDynamic
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.multipart.support.MultipartFilter

/**
 * Required magic for actually being able to upload a file
 * @author john.valetino
 */
@CompileDynamic
@Configuration
@SuppressWarnings(['UnnecessarySetter'])
class UploadConfig {

    @Bean
    CommonsMultipartResolver commonsMultipartResolver() {
        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver()
        commonsMultipartResolver.setMaxUploadSize(-1)
        commonsMultipartResolver
    }

    @Bean
    FilterRegistrationBean multipartFilterRegistrationBean() {
        final MultipartFilter multipartFilter = new MultipartFilter()
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter)
        filterRegistrationBean.addInitParameter(
                'multipartResolverBeanName', 'commonsMultipartResolver')
        filterRegistrationBean
    }

}
