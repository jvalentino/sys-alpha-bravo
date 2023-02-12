package com.github.jvalentino.alphabravo.controller

import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * Used for the dashboard
 * @author john.valentino
 */
@Controller
@Slf4j
@CompileDynamic
class DashboardController {

    @GetMapping('/dashboard')
    String dashboard() {
        log.info('Rendering index')

        'dashboard'
    }

}
