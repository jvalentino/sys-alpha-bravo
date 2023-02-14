package com.github.jvalentino.alphabravo.controller

import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * Used for managing document uploads
 * @author john.valentino
 */
@CompileDynamic
@Controller
@Slf4j
class UploadController {

    @GetMapping('/upload')
    String index() {
        'upload'
    }

}
