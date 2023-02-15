package com.github.jvalentino.alphabravo.controller

import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.view.RedirectView

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

    @PostMapping('/upload-file')
    RedirectView upload(@RequestParam('file') MultipartFile file) {
        log.info(file.originalFilename)

        RedirectView redirectView = new RedirectView('/upload', true)
        redirectView
    }

}
