package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Danish TIN should")
public class DKValidatorTest {

    @ParameterizedTest
    @DisplayName("contain ten digits")
    @CsvSource(value = {
            "1111111118, true",
            "11111111111, false",
            "1, false",
            "999999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "1111111118, true",
            "111111a111, false",
            "xxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "1111111118, true",
            "0101111113, true",
            "1111111211, false",
            "0111111113, false"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert day between 1..31")
    @CsvSource(value = {
            "0101111113, true",
            "4101111113, false"
    })
    void tinShouldAssertCorrectDay(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert month between 1..12")
    @CsvSource(value = {
            "0101111113, true",
            "0100111113, false"
    })
    void tinShouldAssertCorrectMonth(final String tin, final boolean expectedResult){
        Validator v = new DKValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
