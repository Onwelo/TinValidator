package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("German TIN should")
public class DEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain nine digits")
    @CsvSource(value = {
            "111111119, true",
            "1111111111, false",
            "1, false",
            "99999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new DEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "111111119, true",
            "1111a1119, false",
            "xxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new DEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "111111125, true",
            "812321109, true",
            "111111115, false",
            "812321129, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new DEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
