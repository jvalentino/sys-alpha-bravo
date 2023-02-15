package com.github.jvalentino.alphabravo.controller

import com.github.jvalentino.alphabravo.entity.Doc
import com.github.jvalentino.alphabravo.model.ViewVersionModel
import com.github.jvalentino.alphabravo.service.DocService
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Controller used for viewing document versions
 * @author john.valentino
 */
@Slf4j
@CompileDynamic
@Controller
class ViewVersionController {

    @Autowired
    DocService docService

    @GetMapping('/view-versions/{docId}')
    String index(@PathVariable(value='docId') Long docId, Model model) {
        ViewVersionModel result = new ViewVersionModel()
        result.with {
            doc = docService.retrieveDocVersions(docId)
        }

        log.info("Doc ${docId} has ${result.doc.versions.size()} versions")

        model.addAttribute('model', result)

        'view-versions'
    }

}
