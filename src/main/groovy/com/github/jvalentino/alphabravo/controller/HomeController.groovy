package com.github.jvalentino.alphabravo.controller

import com.github.jvalentino.alphabravo.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
//@RequestMapping("/")
class HomeController {

    @Autowired
    UserService userService

    @GetMapping("/")
    String index() {
        println userService.countCurrentUsers() + " current users"
        return "index";
    }

}
