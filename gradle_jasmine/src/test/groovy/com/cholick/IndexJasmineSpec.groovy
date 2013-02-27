package com.cholick

import geb.spock.GebReportingSpec
import spock.lang.Shared

class IndexJasmineSpec extends GebReportingSpec {

    @Shared
    def failures
    @Shared
    def title

    def setupSpec() {
        go 'http://localhost:9450/gradle_jasmine/jasmine/SpecRunner.html'
        title = page.title
        failures = $(class: 'fail').collect { it.text() } ?: [false]
    }


    def "Successfully loaded spec"() {
        expect:
        title == 'Jasmine Spec Runner'
    }

    def "SpecRunner succeeds"() {
        expect:
        !failureText

        where:
        failureText << failures
    }

}
