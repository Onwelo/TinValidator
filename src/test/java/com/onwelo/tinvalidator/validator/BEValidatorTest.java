package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Belgian TIN should")
public class BEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eleven digits")
    @CsvSource(value = {
            "11111111161, true",
            "111111111111, false",
            "1, false",
            "9999999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "11111111161, true",
            "111111a1111, false",
            "xxxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "11111111161, true",
            "00012511119, true",
            "11111112161, false",
            "10012511119, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert month between 0..12")
    @CsvSource(value = {
            "11111111161, true",
            "11311111107, false"
    })
    void tinShouldAssertCorrectMonth(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert day between 0..31")
    @CsvSource(value = {
            "11111111161, true",
            "11115111125, false"
    })
    void tinShouldAssertCorrectDay(final String tin, final boolean expectedResult){
        Validator v = new BEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
