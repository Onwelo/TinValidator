package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Danish TIN should")
public class DKValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eight digits")
    @CsvSource(value = {
            "11111118, true",
            "111111111, false",
            "1, false",
            "9999999, false"
    })
    void tinShouldContainEightDigits(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "11111118, true",
            "1111a111, false",
            "xxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "11111211, true",
            "01011111, true",
            "11111118, false",
            "01111113, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
