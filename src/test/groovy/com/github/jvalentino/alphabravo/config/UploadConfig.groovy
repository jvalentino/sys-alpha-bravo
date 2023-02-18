package com.github.jvalentino.alphabravo.config

import groovy.transform.CompileDynamic
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.multipart.support.MultipartFilter

/**
 * Commons upload screws up Spring Boot Test, so I have this class override the one on
 * the main classpath to not mess it up
 * @author john.valetino
 */
@CompileDynamic
@Configuration
@SuppressWarnings(['UnnecessarySetter'])
class UploadConfig {

}
