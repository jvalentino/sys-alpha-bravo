package com.github.jvalentino.alphabravo.controller

import com.github.jvalentino.alphabravo.model.User
import com.github.jvalentino.alphabravo.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@Slf4j
class LoginController {

    @Autowired
    AuthenticationManager authenticationManager

    @Autowired
    UserService userService

    // https://stackoverflow.com/questions/29794096/spring-security-authentication-authorization-via-rest-endpoint
    // https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
    @PostMapping("/custom-login")
    RedirectView login(@ModelAttribute("user") User user) {
        userService.login(user, authenticationManager)
    }

}
