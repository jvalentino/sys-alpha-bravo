package com.github.jvalentino.alphabravo.controller

import com.github.jvalentino.alphabravo.model.User
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@Slf4j
class LoginController {

    @Autowired
    AuthenticationManager authenticationManager

    @PostMapping("/custom-login")
    public RedirectView login(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        log.info('login')
        final RedirectView redirectView = new RedirectView("/dashboard", true);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.email, user.password));
        boolean isAuthenticated = true
        if (isAuthenticated) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        return redirectView;
    }

}
