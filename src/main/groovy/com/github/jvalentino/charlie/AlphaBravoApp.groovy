package com.github.jvalentino.charlie

import groovy.transform.CompileDynamic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

/**
 * Main application
 */
@SpringBootApplication
@CompileDynamic
class AlphaBravoApp extends SpringBootServletInitializer {

    static void main(String[] args) {
        SpringApplication.run(AlphaBravoApp, args)
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(AlphaBravoApp)
    }

}
