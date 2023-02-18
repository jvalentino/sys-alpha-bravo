package com.github.jvalentino.charlie.controller

import com.github.jvalentino.charlie.entity.AuthUser
import com.github.jvalentino.charlie.entity.Doc
import com.github.jvalentino.charlie.entity.DocVersion
import com.github.jvalentino.charlie.model.ViewVersionModel
import com.github.jvalentino.charlie.repo.AuthUserRepo
import com.github.jvalentino.charlie.util.BaseIntg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MvcResult
import org.springframework.web.servlet.ModelAndView

import java.sql.Timestamp

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ViewVersionControllerIntgTest extends BaseIntg {

    @Autowired
    AuthUserRepo authUserRepo

    def "test versions"() {
        given:
        this.mockAdminLoggedIn()

        and:
        AuthUser user = authUserRepo.findAdminUser('admin').first()

        Doc doc = new Doc()
        doc.with {
            name = 'alpha.pdf'
            createdByUser = user
            updatedByUser = user
            mimeType = 'application/json'
            createdDateTime = new Timestamp(new Date().time)
            updatedDateTime = new Timestamp(new Date().time)
        }
        this.entityManager.persist(doc)

        DocVersion version = new DocVersion(doc:doc)
        version.with {
            versionNum = 1L
            createdDateTime = new Timestamp(new Date().time)
            createdByUser = user
        }
        this.entityManager.persist(version)

        when:
        MvcResult response = mvc.perform(
                get("/view-versions/${doc.docId}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()

        then:
        ModelAndView modelAndView = response.modelAndView
        modelAndView.getViewName() == 'view-versions'

        and:
        ViewVersionModel model = modelAndView.model.model
        model.doc.name == 'alpha.pdf'
        model.doc.versions.size() == 1
        model.doc.versions.first().versionNum == 1
    }

    def "test download version"() {
        given:
        this.mockAdminLoggedIn()

        and:
        AuthUser user = authUserRepo.findAdminUser('admin').first()

        Doc doc = new Doc()
        doc.with {
            name = 'alpha.txt'
            createdByUser = user
            updatedByUser = user
            mimeType = 'text/plain'
            createdDateTime = new Timestamp(new Date().time)
            updatedDateTime = new Timestamp(new Date().time)
        }
        this.entityManager.persist(doc)

        DocVersion version = new DocVersion(doc:doc)
        version.with {
            versionNum = 1L
            createdDateTime = new Timestamp(new Date().time)
            createdByUser = user
            data = "this is a test".bytes
        }
        this.entityManager.persist(version)

        when:
        MvcResult response = mvc.perform(
                get("/version/download/${version.docVersionId}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()

        then:
        response
        response.response.contentType == 'text/plain'
        new String(response.response.getContentAsByteArray()) == 'this is a test'

    }

}
