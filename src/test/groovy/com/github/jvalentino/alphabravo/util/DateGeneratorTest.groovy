package com.github.jvalentino.alphabravo.util

import spock.lang.Specification

class DateGeneratorTest extends Specification {

    def "Test DateGenerator"() {
        when:
        Date result = DateGenerator.date()

        then:
        result
    }
}
