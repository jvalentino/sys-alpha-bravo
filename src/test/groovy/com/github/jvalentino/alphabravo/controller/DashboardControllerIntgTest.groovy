package com.github.jvalentino.alphabravo.controller

import com.github.jvalentino.alphabravo.entity.AuthUser
import com.github.jvalentino.alphabravo.entity.Doc
import com.github.jvalentino.alphabravo.repo.AuthUserRepo
import com.github.jvalentino.alphabravo.service.UserService
import com.github.jvalentino.alphabravo.util.BaseIntg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.servlet.ModelAndView

import java.sql.Timestamp

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class DashboardControllerIntgTest extends BaseIntg {

    @Autowired
    AuthUserRepo authUserRepo

    @Autowired
    UserService userService

    def "test dashboard"() {
        given:
        this.mockAdminLoggedIn()
/*
        and: 'make the admin user have created some documents'
        AuthUser user = authUserRepo.findAdminUser('admin').first()
        AuthUser user = userService.saveNewUser('foo', 'first', 'last', 'pword')

        Doc doc = new Doc()
        doc.with {
            name = 'alpha'
            createdByUser = user
            updatedByUser = user
            mimeType = 'application/json'
            createdDateTime = new Timestamp(new Date().time)
            updatedDateTime = new Timestamp(new Date().time)
        }
        this.entityManager.persist(doc)
*/
        when:
        MvcResult response = mvc.perform(
                get("/dashboard"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()

        then:
        ModelAndView modelAndView = response.modelAndView
        modelAndView.getViewName() == 'dashboard'
    }

    def "test dashboard no auth"() {
        when:
        MvcResult response = mvc.perform(
                get("/dashboard"))
                .andDo(print())
                .andReturn()

        then:
        response.response.status == 403
    }

}
