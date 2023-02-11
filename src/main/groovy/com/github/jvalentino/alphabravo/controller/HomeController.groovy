package com.github.jvalentino.alphabravo.controller

import com.github.jvalentino.alphabravo.model.HomeModel
import com.github.jvalentino.alphabravo.model.User
import com.github.jvalentino.alphabravo.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
//@RequestMapping("/")
@Slf4j
class HomeController {

    @Autowired
    UserService userService

    @GetMapping("/")
    String index(Model model) {
        log.info('Rendering index')
        HomeModel response = new HomeModel()
        response.with {
            users = userService.countCurrentUsers()
        }
        model.addAttribute('model', response)
        model.addAttribute('user', new User())
        return "index";
    }

    @GetMapping("/invalid")
    String index() {
        'invalid'
    }

}
