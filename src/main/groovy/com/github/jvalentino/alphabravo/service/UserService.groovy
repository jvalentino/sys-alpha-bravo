package com.github.jvalentino.alphabravo.service

import com.github.jvalentino.alphabravo.entity.AuthUser
import com.github.jvalentino.alphabravo.repo.AuthUserRepo
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
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
        log.info('Checking to see if we need to create a default admin user...')
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

    boolean isValidUser(String email, String password) {
        log.info("Checking to see if ${email} is a valid user with its given password...")
        List<AuthUser> users = authUserRepo.findAdminUser(email)

        if (users.size() == 0) {
            log.info("${email} doesn't have any email matches, so not valid")
            return false
        }

        AuthUser user = users.first()
        String expected = Md5Crypt.md5Crypt(password.bytes, user.salt)

        if (expected == user.password) {
            log.info("Email ${email} gave a password that matches the salted MD5 hash")
            return true
        }

        log.info("Email ${email} gave a passowrd that doesn't match the salted MD5 hash")
        false
    }

    int countCurrentUsers() {
        authUserRepo.findAll().size()
    }

    Authentication authenticate(Authentication authentication) {
        UsernamePasswordAuthenticationToken auth = authentication

        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        log.info("Authenticating ${email}...")

        // if they have not logged in, do so
        if (email == 'anonymousUser') {
            log.info('Not logged in to we have to first login...')
            if (this.isValidUser(auth.getPrincipal(), auth.getCredentials())) {
                return authentication
            }

            throw new Exception('Invalid username and/or password')
        }

        // they are already logged in
        log.info("${email} is already logged in")
        authentication
    }

    protected String generateSalt() {
        '$1$' + B64.getRandomSalt(8)
    }

}
