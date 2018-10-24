package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Belgian TIN should")
public class BEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain ten digits")
    @CsvSource(value = {
            "0111111161, true",
            "01111111111, false",
            "01, false",
            "9999999999, false"
    })
    void tinShouldContainElevenDigits(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "0111111161, true",
            "01111a1111, false",
            "xxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "0897224264, true",
            "886122516, true",
            "0877224264, false",
            "886132516, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
