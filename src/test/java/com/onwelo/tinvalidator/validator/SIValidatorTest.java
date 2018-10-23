package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Slovenian TIN should")
class SIValidatorTest {

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
        Validator v = new SIValidator();
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
        Validator v = new SIValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain first digit greater than zero")
    @CsvSource(value = {
            "15121333, true",
            "10414318, true",
            "10830531, true",
            "00331678, false",
            "00031678, false",
            "00000678, false"
    })
    void tinShouldContainFirstDigitGreaterThanZero(final String tin, final boolean expectedResult){
        Validator v = new SIValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum")
    @CsvSource(value = {
            "17659957, true",
            "15779092, true",
            "24995975, true",
            "44156111, true",
            "64756991, false",
            "66694125, false",
            "77655431, false",
            "83355612, false",
            "41422312, false",
            "56243689, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new SIValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
