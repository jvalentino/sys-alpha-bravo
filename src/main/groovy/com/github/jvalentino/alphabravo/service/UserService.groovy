package com.github.jvalentino.alphabravo.service

import com.github.jvalentino.alphabravo.repo.AuthUserRepo
import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * A general service used for dealing with users
 * @author john.valentino
 */
@CompileDynamic
@Service
class UserService {

    @Autowired
    AuthUserRepo authUserRepo

    int countCurrentUsers() {
        authUserRepo.findAll().size()
    }

}
