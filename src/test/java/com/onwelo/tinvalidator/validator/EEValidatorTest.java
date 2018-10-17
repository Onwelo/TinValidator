package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Estonian TIN should")
public class EEValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eleven digits")
    @CsvSource(value = {
            "11111111112, true",
            "111111111111, false",
            "1, false",
            "9999999999, false"
    })
    void tinShouldContainTenDigits(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "11111111112, true",
            "111111a1112, false",
            "xxxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "11111111112, true",
            "37102250382, true",
            "32708101201, true",
            "46304280206, true",
            "11111111212, false",
            "37112250382, false",
            "32709101201, false",
            "46304286206, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert day between 1..31")
    @CsvSource(value = {
            "37102250382, true",
            "37102450382, false"
    })
    void tinShouldAssertCorrectDay(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert month between 1..12")
    @CsvSource(value = {
            "46304280206, true",
            "46334280206, false"
    })
    void tinShouldAssertCorrectMonth(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert digits 8 to 10 in range 001..710")
    @CsvSource(value = {
            "11111111112, true",
            "11111118102, false"
    })
    void tinShouldAssertCorrectValue(final String tin, final boolean expectedResult){
        Validator v = new EEValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
