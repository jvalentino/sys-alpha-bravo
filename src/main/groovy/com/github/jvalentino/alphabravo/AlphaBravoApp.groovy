package com.github.jvalentino.alphabravo

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan('com.github.jvalentino.alphabravo')
class AlphaBravoApp extends SpringBootServletInitializer {

	static void main(String[] args) {
		SpringApplication.run(AlphaBravoApp, args)
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AlphaBravoApp)
	}

}
