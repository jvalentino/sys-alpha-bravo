package com.github.jvalentino.alphabravo.service

import com.github.jvalentino.alphabravo.repo.DocRepo
import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * General service for interacting with documents
 * @author john.valentino
 */
@CompileDynamic
@Service
class DocService {

    @Autowired
    DocRepo docRepo

    Long countDocuments() {
        docRepo.count()
    }

}
