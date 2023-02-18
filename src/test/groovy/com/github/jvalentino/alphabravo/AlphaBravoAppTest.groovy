package com.github.jvalentino.alphabravo

import org.springframework.boot.SpringApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import spock.lang.Specification

class AlphaBravoAppTest extends Specification {

    def setup() {
        GroovyMock(SpringApplication, global:true)
    }

    def "test main"() {
        when:
        AlphaBravoApp.main(null)

        then:
        1 * SpringApplication.run(AlphaBravoApp, null)
    }

    def "Test configure"() {
        given:
        AlphaBravoApp subject = new AlphaBravoApp()
        SpringApplicationBuilder builder = GroovyMock()

        when:
        subject.configure(builder)

        then:
        1 *  builder.sources(AlphaBravoApp)
    }
}
