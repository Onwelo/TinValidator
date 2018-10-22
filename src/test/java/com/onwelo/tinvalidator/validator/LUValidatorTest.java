package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Cypriot TIN should")
class LUValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine or thirteen digits")
    @CsvSource(value = {
            "1234567890123, true",
            "12345678901, true",
            "11111111, true",
            "1111111111, false",
            "111111111111, false",
            "11111111111111, false",
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
            "12345678901, true",
            "1234567890a, false",
            "12345678, true",
            "1234567a, false",
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
            "1893120105733, false",
            //missing correct 9 digit TIN
            "99999999991, false",
            "10000356, true",
            "00001515, true",
            "10000350, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new LUValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
