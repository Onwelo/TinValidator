package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Croatian TIN should")
public class HRValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eleven digits")
    @CsvSource(value = {
            "11111111119, true",
            "111111111111, false",
            "1, false",
            "9999999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new HRValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "11111111119, true",
            "111111a1119, false",
            "xxxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new HRValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "11111111119, true",
            "94577403194, true",
            "11111112119, false",
            "94575403194, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new HRValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
