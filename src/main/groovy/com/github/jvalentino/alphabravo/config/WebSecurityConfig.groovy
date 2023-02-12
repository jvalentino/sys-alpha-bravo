package com.github.jvalentino.alphabravo.config

import com.github.jvalentino.alphabravo.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder

@Configuration
@EnableWebSecurity
@Slf4j
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/resources/**",
                        "/webjars/**",
                        "/",
                        '/custom-login',
                        '/invalid'
                ).permitAll()
                .anyRequest().authenticated()

    }

    // https://stackoverflow.com/questions/4664893/how-to-manually-set-an-authenticated-user-in-spring-security-springmvc
    @Bean
    AuthenticationManager customAuthenticationManager() throws Exception {
        return new AuthenticationManager() {
            @Override
            Authentication authenticate(Authentication authentication) throws AuthenticationException {
                userService.authenticate(authentication)
            }
        }
    }
}
