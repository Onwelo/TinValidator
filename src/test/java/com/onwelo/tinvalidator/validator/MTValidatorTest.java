package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Maltese TIN should")
class MTValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eight digits")
    @CsvSource(value = {
            "12345678, true",
            "62355678, true",
            "52331678, true",
            "42345644, true",
            "32245644, true",
            "12244, false",
            "244, false",
            "242341451244, false",
            "2423414512241515125, false",
            "2, false",
    })
    void tinShouldContainEightDigits(final String tin, final boolean expectedResult){
        Validator v = new MTValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain only digits")
    @CsvSource(value = {
            "12345678, true",
            "62355678, true",
            "52331678, true",
            "a2233v6s, false",
            "523ddfsa, false",
            "s2s1saag, false"
    })
    void tinShouldContainOnlyDigits(final String tin, final boolean expectedResult){
        Validator v = new MTValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain first six digits which combined are greater than 100000")
    @CsvSource(value = {
            "15121333, true",
            "10414318, true",
            "10830531, true",
            "00331678, false",
            "00031678, false",
            "00000678, false"
    })
    void tinShouldContainFirstSixDigitsWhichCombinedAreGreaterThan100000(final String tin, final boolean expectedResult){
        Validator v = new MTValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum")
    @CsvSource(value = {
            "15121333, true",
            "10414318, true",
            "10830531, true",
            "67230533, false",
            "40330534, false",
            "33830589, false",
            "13320434, false",
            "10544555, false",
            "22244555, false",
            "22334455, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new MTValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
