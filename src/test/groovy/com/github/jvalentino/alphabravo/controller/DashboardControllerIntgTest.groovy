package com.github.jvalentino.alphabravo.controller

import com.github.jvalentino.alphabravo.util.BaseIntg
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.servlet.ModelAndView

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class DashboardControllerIntgTest extends BaseIntg {

    def "test dashboard"() {
        given:
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken('admin', null)
        SecurityContextHolder.getContext().setAuthentication(authentication)

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
