package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Cypriot TIN should")
class CYValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eight digits and one letter on the end")
    @CsvSource(value = {
            "00123123T, true",
            "11111111111, false",
            "1, false",
            "99999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new CYValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("have only one letter as a last character")
    @CsvSource(value = {
            "11111111E, true",
            "111111a11, false",
            "xxxxxxxxx, false",
            "999999999, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new CYValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "11111111E, true",
            "00123123T, true",
            "99652156X, true",
            "11111112X, false",
            "10123123T, false",
            "98652156X, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new CYValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
