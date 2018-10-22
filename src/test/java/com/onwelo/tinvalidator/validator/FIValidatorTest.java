package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Finnish TIN should")
public class FIValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eight characters")
    @CsvSource(value = {
            "00000027, true",
            "111111111, false",
            "1, false"
    })
    void tinShouldContainEightDigits(final String tin, final boolean expectedResult){
        Validator v = new FIValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "00000027, true",
            "10000a27, false",
            "xxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new FIValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "09853608, true",
            "15380325, true",
            "09833608, false",
            "15381325, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new FIValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
