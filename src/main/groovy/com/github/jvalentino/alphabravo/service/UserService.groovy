package com.github.jvalentino.alphabravo.service

import com.github.jvalentino.alphabravo.entity.AuthUser
import com.github.jvalentino.alphabravo.repo.AuthUserRepo
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.apache.commons.codec.digest.Md5Crypt
import org.apache.commons.codec.digest.B64

import javax.annotation.PostConstruct
import java.security.MessageDigest
import java.security.SecureRandom

/**
 * A general service used for dealing with users
 * @author john.valentino
 */
@CompileDynamic
@Service
@Slf4j
class UserService {

    @Autowired
    AuthUserRepo authUserRepo

    @PostConstruct
    void init(){
        List<AuthUser> users = authUserRepo.findAdminUser('admin')

        if (users.size() != 0) {
            return
        }

        log.info('There are zero admin users, so we are going to now create one')

        String randomSalt = generateSalt()
        String generatedPassword = UUID.randomUUID().toString()

        AuthUser user = new AuthUser()
        user.with {
            email = 'admin'
            firstName = 'admin'
            lastName = 'admin'
            salt = randomSalt
            password = Md5Crypt.md5Crypt(generatedPassword.bytes, randomSalt)
        }

        authUserRepo.save(user)

        log.info('===========================================================')
        log.info('New User Created')
        log.info("Email: ${user.email}")
        log.info("Password: ${generatedPassword}")
        // For example: af99a7dd-4f7c-434d-909c-e655158d44a0
        log.info('===========================================================')
    }

    int countCurrentUsers() {
        authUserRepo.findAll().size()
    }

    protected String generateSalt() {
        '$1$' + B64.getRandomSalt(8)
    }

}
