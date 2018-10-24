package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Lithuanian TIN should")
public class LTValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine or twelve digits")
    @CsvSource(value = {
            "111111111112, true",
            "111111111, true",
            "1111111111111, false",
            "1, false",
            "9999999999, false"
    })
    void tinShouldContainElevenDigits(final String tin, final boolean expectedResult){
        Validator v = new LTValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "111111111112, true",
            "1111111a1112, false",
            "xxxxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new LTValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "100000031710, true",
            "100001647810, true",
            "115184219, true",
            "119505811, true",
            "100100031710, false",
            "100101647810, false",
            "116184219, false",
            "119515811, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new LTValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
