package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Austrian TIN should")
public class ATValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine digits")
    @CsvSource(value = {
            "931736581, true",
            "11111111111, false",
            "1, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new ATValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "111111118, true",
            "111111a11, false",
            "xxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new ATValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "111111118, true",
            "931736581, true",
            "111111123, false",
            "931736582, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new ATValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
