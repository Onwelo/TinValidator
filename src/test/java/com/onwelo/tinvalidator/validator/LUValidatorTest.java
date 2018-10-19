package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Cypriot TIN should")
class LUValidatorTest {

    @ParameterizedTest
    @DisplayName("contain thirteen digits")
    @CsvSource(value = {
            "1234567890123, true",
            "111111111111, false",
            "1, false",
            "99999333333999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new LUValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters but last character")
    @CsvSource(value = {
            "1234567890123, true",
            "111111a111111, false",
            "xxxxxxxxxxxxx, false",
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new LUValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "1893120105732, true",
            "1893120105733, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new LUValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
