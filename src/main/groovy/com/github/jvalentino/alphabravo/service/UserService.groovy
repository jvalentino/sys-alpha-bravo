package com.github.jvalentino.alphabravo.service

import com.github.jvalentino.alphabravo.entity.AuthUser
import com.github.jvalentino.alphabravo.model.User
import com.github.jvalentino.alphabravo.repo.AuthUserRepo
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.apache.commons.codec.digest.Md5Crypt
import org.apache.commons.codec.digest.B64
import org.springframework.web.servlet.view.RedirectView

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

        String generatedPassword = UUID.randomUUID().toString()
        AuthUser user = this.saveNewUser('admin', 'admin', 'admin', generatedPassword)

        log.info('===========================================================')
        log.info('New User Created')
        log.info("Email: ${user.email}")
        log.info("Password: ${generatedPassword}")
        // For example: 0421908e-2285-4142-93ed-b5c060e4fcc4
        log.info('===========================================================')

    }

    RedirectView login(User user, AuthenticationManager authenticationManager) {
        log.info('Attempting to login the user user by email of ' + user.email)

        try {
            RedirectView redirectView = new RedirectView("/dashboard", true)
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.email, user.password));
            SecurityContextHolder.getContext().setAuthentication(authentication)
            return redirectView
        } catch (e) {
            log.error("${user.email} gave invalid credentials", e)
        }

        RedirectView redirectView = new RedirectView("/invalid", true)
        return redirectView
    }

    AuthUser saveNewUser(String email, String firstName, String lastName, String plaintextPassword) {
        String randomSalt = generateSalt()

        AuthUser user = new AuthUser(email:email, firstName:firstName, lastName:lastName)
        user.with {
            salt = randomSalt
            password = Md5Crypt.md5Crypt(plaintextPassword.bytes, randomSalt)
        }

        authUserRepo.save(user)
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
