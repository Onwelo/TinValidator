package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Finnish TIN should")
public class FIValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eleven characters")
    @CsvSource(value = {
            "131052-308T, true",
            "111111-1111C, false",
            "1, false",
            "999999999, false"
    })
    void tinShouldContainElevenDigits(final String tin, final boolean expectedResult){
        Validator v = new FIValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain letters only in 7th and 11th position")
    @CsvSource(value = {
            "131052-308T, true",
            "131052A308W, true",
            "1111111a111, false",
            "xxxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new FIValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "111111-111C, true",
            "131052-308T, true",
            "11111111211, false",
            "92727319561, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new FIValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
