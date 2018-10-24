package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Estonian TIN should")
public class EEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine digits")
    @CsvSource(value = {
            "111111112, true",
            "1111111111, false",
            "1, false",
            "99999999, false"
    })
    void tinShouldContainNineDigits(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "111111112, true",
            "1111a1112, false",
            "xxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "100007796, true",
            "101367804, true",
            "100027796, false",
            "101467804, false",
            "110007796, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
