package com.onwelo.tinvalidator.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Romanian TIN should")
class ROValidatorTest {
    
    @ParameterizedTest
    @DisplayName("contain at least two and no more then ten digits")
    @CsvSource(value = {
            "12, true",
            "43, true",
            "1234567890, true",
            "451355466, true",
            "2, false",
            "12345678905325325, false",
            "3333125236375907036235151531532, false",

    })
    void tinShouldContainAtLeastTwoAndNoMoreThenTenDigits(final String tin, final boolean expectedResult){
        Validator v = new ROValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain only digits")
    @CsvSource(value = {
            "1234567890, true",
            "7544541295, true",
            "123aaa7890, false",
            "$$!4567890, false",

    })
    void tinShouldContainOnlyDigits(final String tin, final boolean expectedResult){
        Validator v = new ROValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }

    @ParameterizedTest
    @DisplayName("contain first digit greater then zero")
    @CsvSource(value = {
            "1234567890, true",
            "7544541295, true",
            "0544541295, false",
            "0544541295, false",

    })
    void tinShouldContainFirstDigitGreaterThanZero(final String tin, final boolean expectedResult){
        Validator v = new ROValidator();
        Assertions.assertEquals(expectedResult, v.matchRegex(tin));
    }


    @ParameterizedTest
    @DisplayName("compute control sum")
    @CsvSource(value = {
            "11198699, true",
            "99908, true",
            "16795469, true",
            "16914128, true",
            "11134288, true",
            "33, false",
            "99999999, false",
            "44412, false",
            "4412, false",
            "7658321, false",
    })
    void tinShouldComputeControlSum(final String tin, final boolean expectedResult){
        Validator v = new ROValidator();
        Assertions.assertEquals(expectedResult, v.computeControlSum(tin));
    }
    
}
