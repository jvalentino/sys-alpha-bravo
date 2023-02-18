package com.github.jvalentino.charlie.controller

import com.github.jvalentino.charlie.model.HomeModel
import com.github.jvalentino.charlie.model.User
import com.github.jvalentino.charlie.service.DocService
import com.github.jvalentino.charlie.service.UserService
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * Controller used for the initial landing page
 * @author john.valentino
 */
@Controller
@Slf4j
@CompileDynamic
class HomeController {

    @Autowired
    UserService userService

    @Autowired
    DocService docService

    @GetMapping('/')
    String index(Model model) {
        log.info('Rendering index')
        HomeModel response = new HomeModel()
        response.with {
            users = userService.countCurrentUsers()
            documents = docService.countDocuments()
        }
        model.addAttribute('model', response)
        model.addAttribute('user', new User())
        'index'
    }

    @GetMapping('/invalid')
    String invalid() {
        'invalid'
    }

}
