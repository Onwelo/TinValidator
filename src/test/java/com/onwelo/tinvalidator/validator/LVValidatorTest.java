package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Latvian TIN should")
public class LVValidatorTest {

    @ParameterizedTest
    @DisplayName("contain eleven digits")
    @CsvSource(value = {
            "11111111118, true",
            "111111111111, false",
            "1, false",
            "9999999999, false"
    })
    void tinShouldContainElevenDigits(final String tin, final boolean expectedResult){
        Validator v = new LVValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("not contain letters")
    @CsvSource(value = {
            "11111111118, true",
            "1111111a111, false",
            "xxxxxxxxxxx, false"
    })
    void tinShouldNotContainLetters(final String tin, final boolean expectedResult){
        Validator v = new LVValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("compute control sum correctly")
    @CsvSource(value = {
            "01011012345, true",
            "32579461005, true",
            "07091910933, true",
            "40103446084, true",
            "40103592648, true",
            "40103619251, true",
            "41202010448, true",
            "41031037436, true",
            "41503031291, true"
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new LVValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert day between 1..31")
    @CsvSource(value = {
            "01011012345, true",
            "00011012345, false"
    })
    void tinShouldAssertCorrectDay(final String tin, final boolean expectedResult){
        Validator v = new LVValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }

    @ParameterizedTest
    @DisplayName("assert month between 0..12")
    @CsvSource(value = {
            "01011012345, true",
            "01611012345, false"
    })
    void tinShouldAssertCorrectMonth(final String tin, final boolean expectedResult){
        Validator v = new LVValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
}
