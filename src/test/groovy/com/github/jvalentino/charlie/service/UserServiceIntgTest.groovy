package com.github.jvalentino.charlie.service

import com.github.jvalentino.charlie.model.User
import com.github.jvalentino.charlie.util.BaseIntg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.servlet.view.RedirectView

class UserServiceIntgTest extends BaseIntg {

    @Autowired
    AuthenticationManager authenticationManager

    @Autowired
    UserService userService

    def "test login"() {
        given:
        User user = new User(email:'alpha', password:'bravo')
        userService.saveNewUser('alpha', 'first', 'last', 'bravo')

        when:
        RedirectView result = userService.login(user, authenticationManager)

        then:
        result.getUrl() == '/dashboard'
    }

    def "test login with invalid"() {
        given:
        User user = new User(email:'alpha', password:'bravo')

        when:
        RedirectView result = userService.login(user, authenticationManager)

        then:
        result.getUrl() == '/invalid'
    }
}
