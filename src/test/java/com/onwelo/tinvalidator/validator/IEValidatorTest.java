package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Irish TIN should")
public class IEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain seven digits and one or two letters")
    @CsvSource(value = {
            "1234567T, true",
            "1234567TW, true",
            "1234577W, true",
            "1234577WW, true",
            "1234577IA, true",
            "1111111AAA, false",
            "1A, false",
            "99999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new IEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters but last one or two characters")
    @CsvSource(value = {
            "1234567T, true",
            "1234567TW, true",
            "1234577W, true",
            "1234577WW, true",
            "1234577IA, true",
            "111111a11, false",
            "xxxxxxxxx, false",
            "999999999, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new IEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "1234567T, true",
            "1234567TW, true",
            "1234577W, true",
            "1234577WW, true",
            "1234577IA, true",
            "1111111AB, false",
            "1234577AW, false",
            "1234577E, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new IEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
